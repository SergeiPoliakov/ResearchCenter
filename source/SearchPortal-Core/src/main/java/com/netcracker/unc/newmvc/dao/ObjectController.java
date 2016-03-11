package com.netcracker.unc.newmvc.dao;

import java.util.ArrayList;
import java.util.List;

public class ObjectController {

	// return last creating object
	public ObjectModel getLastCreatingObject(List<ObjectModel> previouesObjects, int userId) {
		ObjectModel object = new ObjectModel();
		UserDAO userDAO = new UserDAO();
		UserModel user = userDAO.getUser(userId);
		ArrayList<Integer> previouesObjectsId = new ArrayList<Integer>();
		int objectId = 0;

		for (ObjectModel obj : previouesObjects)
			previouesObjectsId.add(obj.getFinObjectId());

		for (ObjectModel obj : user.getAllObjects()) {
			boolean check = false;
			for (Integer id : previouesObjectsId) {
				if (id == obj.getFinObjectId()) {
					check = true;
					break;
				}
			}
			if (!check) {
				objectId = obj.getFinObjectId();
				break;
			}
		}

		for (ObjectModel obj : user.getAllObjects()) {
			if (obj.getFinObjectId() == objectId)
				object = obj;
		}

		return object;
	}

}
