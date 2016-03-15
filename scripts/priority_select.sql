
SELECT 
 FIN_OBJECT_ID as OBJECT_ID,
 USER_ID,
 OBJECT_NAME,
 (
 SELECT coef_par.VALUE
 FROM SP_PARAMS coef_par
 WHERE coef_par.ATTRIBUTE_ID = 1 
  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID
 ) as coefficient,
 (
 SELECT coef_par.VALUE
 FROM SP_PARAMS coef_par
 WHERE coef_par.ATTRIBUTE_ID = 2 
  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID
 ) as min_percent,
 (
 SELECT coef_par.VALUE
 FROM SP_PARAMS coef_par
 WHERE coef_par.ATTRIBUTE_ID = 3 
  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID
 ) as max_percent,
 (
  SELECT
   SUM(obj_params.VALUE) as sum_root
  FROM (
   SELECT 
    CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id,
    f_ob.FIN_OBJECT_ID as object_id,
    f_ob.FIN_OBJECT_TYPE_ID as object_type
   FROM SP_FIN_OBJECTS f_ob
   START WITH f_ob.FIN_OBJECT_ID = ANY(
    SELECT join_fo.FIN_OBJECT_ID
    FROM SP_FIN_OBJECTS join_fo 
    WHERE join_fo.FIN_OBJECT_TYPE_ID = 1
   )
  CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID
 ) table_objects_and_its_roots 
  INNER JOIN SP_PARAMS obj_params
   ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID
 WHERE obj_params.ATTRIBUTE_ID = 8
  AND table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID
 GROUP BY table_objects_and_its_roots.root_object_id
) as sum_category
FROM SP_FIN_OBJECTS main_fo
WHERE FIN_OBJECT_TYPE_ID =1;