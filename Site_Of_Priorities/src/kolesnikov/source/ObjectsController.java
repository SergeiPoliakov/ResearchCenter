package kolesnikov.source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ObjectsController {

	private Connection connect = ConnectionFactory.getConnection();
	private Statement statement = null;
	private PreparedStatement prepareState = null;  // для шаблона запроса к БД
	private String query = null; //запрос
	private ResultSet result = null;
	
	// добавляет объект в базу данных
	public boolean addObjectToDatabase(Object object) {
		try {
			statement = connect.createStatement();
			prepareState = connect.prepareStatement("INSERT INTO Pj_Users(login, hash_sum, account_type, salt) VALUES ( ? , ? , ? , ? )");
		
			//если объект типа Users
			if(object instanceof Users) {
				Users user = (Users)object;
				String login = user.getLogin();
				query = "SELECT login FROM pj_users where login like '" + login +"'";
				result = statement.executeQuery(query); // отправляем запрос
				if(!result.next()) {
					prepareState.setString(1, login);
					prepareState.setInt(2, user.getHashSum());
					prepareState.setString(3, user.getAccountType());
					prepareState.setInt(4, user.getSalt());
					prepareState.executeUpdate();
					
					// если пользователь ввел имя
					if(user.getName() != null) {
						query = "UPDATE pj_users SET name = '" + user.getName() + "' WHERE login LIKE '" + login + "'";
						statement.executeUpdate(query); 				
					} 
					return true;
				}
			}
			//если объект типа Cases
			if(object instanceof Cases) {
				Cases caze = (Cases)object;
				int caseTypeId = 0;
				int parentId = 0;
				query = "SELECT fin_object_type_id FROM fin_object_types WHERE fin_object_type_name like '" + caze.getCaseType() + "'";
				result = statement.executeQuery(query);
				//проверяем если такой атрибут
				if(!result.next()) {
					query = "INSERT INTO fin_object_types(fin_object_type_names) VALUES('" + caze.getCaseType() + "')";
					statement.executeUpdate(query);
				}
				statement.close();
				//обновляем данные
				result = statement.executeQuery(query);
				result.next();
				caseTypeId = result.getInt(1);
				
				// проверяем является ли это задача подзадачей другой
				query = "SELECT fin_object_id FROM fin_objects WHERE object_name like '" + caze.getParentTypeName() + "'";
				result = statement.executeQuery(query);
				if(result.next()) {
					parentId = result.getInt(1);
				};
		
				//добавляем описание задачи в БД
				prepareState = connect.prepareStatement("INSERT INTO fin_objects(parent_id, object_name, fin_object_type_id, user_id) VALUES( ? , ? , ? , ? )");
				if(parentId != 0)
					prepareState.setInt(1, parentId);
				prepareState.setString(2, caze.getCaseName());
				prepareState.setInt(3, caseTypeId);
				prepareState.setInt(4, caze.getUserId());
				

			}
			
			
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// возвращает объект из базы данных (метод не полный, тут будут по идее присваиваться все атрибуты юзера из БД)
	public Object getObjectFromeDatabase(Users user) {
		try {
			prepareState = connect.prepareStatement("SELECT * FROM pj_users where login like ?");
			prepareState.setString(1, user.getLogin());
			ResultSet result = prepareState.executeQuery();
			result.next();
			user.setUserId(result.getInt(1));
			user.setLogin(result.getString(2));
			if(result.getString(4) != null)
				user.setName(result.getString(4));
			user.setAccountType(result.getString(5));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//проверяем пароль
	public boolean checkPassword(Users user) {
		int hashSum = user.getHashSum();
		int salt = user.getSalt();
		String login = user.getLogin();
		System.out.println(hashSum);
		System.out.println(salt);
		try {
			prepareState = connect.prepareStatement("SELECT * FROM pj_users WHERE hash_sum = ? and salt = ? and login like ? ");
			prepareState.setInt(1, hashSum);
			prepareState.setInt(2, salt);
			prepareState.setString(3, login);
			ResultSet result = prepareState.executeQuery();
			if(result.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
