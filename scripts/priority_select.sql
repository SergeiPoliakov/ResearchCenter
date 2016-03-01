SELECT 
 main_fo.FIN_OBJECT_ID as object_ID, 
 main_fo.USER_ID,
 main_fo.OBJECT_NAME,
 (
  SELECT select_par.VALUE
   FROM SP_FIN_OBJECTS select_fo 
    INNER JOIN SP_ATTRIBUTES select_pja
     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID
    INNER JOIN SP_PARAMS select_par
     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID
   WHERE select_pja.ATTRIBUTE_NAME='Коэффицент приоритета'
    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID
    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID
 ) as coefficient,
 (
  SELECT select_par.VALUE
   FROM SP_FIN_OBJECTS select_fo 
    INNER JOIN SP_ATTRIBUTES select_pja
     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID
    INNER JOIN SP_PARAMS select_par
     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID
   WHERE select_pja.ATTRIBUTE_NAME='Минимальный % от расхода'
    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID
    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID
 ) as min_percent,
 (
  SELECT select_par.VALUE
  FROM SP_FIN_OBJECTS select_fo 
   INNER JOIN SP_ATTRIBUTES select_pja
    ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID
   INNER JOIN SP_PARAMS select_par
    ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID
  WHERE select_pja.ATTRIBUTE_NAME='Максимальный % от расхода'
   AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID
   AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID
 ) as max_percent,
 (SELECT SYSDATE FROM DUAL
 ) as final_date, --время до конца расчетн. периода может тоже будет влиять
 (
  SELECT
   SUM(obj_params.VALUE) as sum_root
  FROM (
   SELECT 
    f_ob.USER_ID as user_id,
    CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id,
    f_ob.FIN_OBJECT_ID as object_id,
    f_ob.FIN_OBJECT_TYPE_ID as object_type
   FROM SP_FIN_OBJECTS f_ob
   START WITH f_ob.FIN_OBJECT_ID = ANY(
    SELECT join_fo.FIN_OBJECT_ID
    FROM SP_FIN_OBJECTS join_fo 
     INNER JOIN SP_FIN_OBJECT_TYPES join_fot
      ON join_fo.FIN_OBJECT_TYPE_ID = join_fot.FIN_OBJECT_TYPE_ID
    WHERE join_fot.FIN_OBJECT_TYPE_NAME = 'Категория'
   )
  CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID
 ) table_objects_and_its_roots 
  INNER JOIN SP_PARAMS obj_params
   ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID
  INNER JOIN SP_ATTRIBUTES obj_atr
   ON obj_atr.FIN_OBJECT_TYPE_ID = table_objects_and_its_roots.object_type  
 WHERE obj_atr.ATTRIBUTE_NAME = 'Сумма расхода' AND
  obj_params.ATTRIBUTE_ID = obj_atr.ATTRIBUTE_ID AND 
  table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID
 GROUP BY table_objects_and_its_roots.root_object_id
) as sum_category
FROM SP_FIN_OBJECTS main_fo 
 LEFT JOIN SP_FIN_OBJECT_TYPES main_fot
  ON main_fo.FIN_OBJECT_TYPE_ID = main_fot.FIN_OBJECT_TYPE_ID
WHERE main_fot.FIN_OBJECT_TYPE_NAME = 'Категория';