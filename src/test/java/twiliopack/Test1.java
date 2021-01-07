package twiliopack;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class Test1 
{
	public static void main(String[] args) throws Exception
	{
		//Connect to twilio cloud for SMS Service
		String asid="AC51615bfa2dbe3698bafdd5300e307c05";
		String auth="cc8e966a8fd2a9d35ee88031e952ddad";
		Twilio.init(asid,auth);
		ResourceSet<Message> messages=Message.reader().read();
		for(Message message:messages)
		{
			System.out.println(message.getFrom()+"-->"+message.getBody());
		}
	}
}
