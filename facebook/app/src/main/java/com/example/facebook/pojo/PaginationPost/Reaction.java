package com.example.facebook.pojo.PaginationPost;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class Reaction{

	@SerializedName("reactedUserId")
	private String reactedUserId;

	@SerializedName("reactionType")
	private String reactionType;

	@SerializedName("reactionId")
	private String reactionId;

	@SerializedName("postId")
	private String postId;

	@SerializedName("reactedDate")
	private String reactedDate;

	public void setReactedUserId(String reactedUserId){
		this.reactedUserId = reactedUserId;
	}

	public String getReactedUserId(){
		return reactedUserId;
	}

	public void setReactionType(String reactionType){
		this.reactionType = reactionType;
	}

	public String getReactionType(){
		return reactionType;
	}

	public void setReactionId(String reactionId){
		this.reactionId = reactionId;
	}

	public String getReactionId(){
		return reactionId;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setReactedDate(String reactedDate){
		this.reactedDate = reactedDate;
	}

	public String getReactedDate(){
		return reactedDate;
	}

	@Override
 	public String toString(){
		return 
			"Reaction{" + 
			"reactedUserId = '" + reactedUserId + '\'' + 
			",reactionType = '" + reactionType + '\'' + 
			",reactionId = '" + reactionId + '\'' + 
			",postId = '" + postId + '\'' + 
			",reactedDate = '" + reactedDate + '\'' + 
			"}";
		}
}