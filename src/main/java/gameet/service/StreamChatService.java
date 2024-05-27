package gameet.service;


import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import io.getstream.chat.java.exceptions.StreamException;
import io.getstream.chat.java.models.User;
import io.getstream.chat.java.models.User.UserRequestObject;
import io.getstream.chat.java.models.User.UserUpsertResponse;

@Service
public class StreamChatService {

	 public String generaToken(String username) {
		 
		 var calendar = new GregorianCalendar();
		 calendar.add(Calendar.MINUTE, 240);
		 var token = User.createToken(username, calendar.getTime(), null);
		 return token;
	  }
	 
	 public UserUpsertResponse sincronizarUsuarios(String username) {
		 var usersUpsertRequest = User.upsert();
		 usersUpsertRequest.user(UserRequestObject.builder().id(username).name(username).build());

		 try {
			var response = usersUpsertRequest.request();
			 return response;
		} catch (StreamException e) {
			e.printStackTrace();
			return null;
		}
	 }

}