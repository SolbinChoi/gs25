package kr.ac.sungkyul.gs25.exception;

public class UserInfoUpdateException extends RuntimeException{
	public UserInfoUpdateException(){
		super("Exception from Updating User Info");
	}
	
	public UserInfoUpdateException(String message){
		super(message);
	}
}
