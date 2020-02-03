package com.example.facebook.pojo.PaginationPost;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class SubCommentsItem{

	@SerializedName("profilePicture")
	private String profilePicture;

	@SerializedName("commentingUserId")
	private String commentingUserId;

	@SerializedName("commentingUserName")
	private String commentingUserName;

	@SerializedName("commentDate")
	private String commentDate;

	@SerializedName("commentId")
	private String commentId;

	@SerializedName("commentDescription")
	private String commentDescription;

	@SerializedName("parentCommentId")
	private String parentCommentId;

	@SerializedName("postId")
	private String postId;

	@SerializedName("userId")
	private String userId;

	public void setProfilePicture(String profilePicture){
		this.profilePicture = profilePicture;
	}

	public String getProfilePicture(){
		return profilePicture;
	}

	public void setCommentingUserId(String commentingUserId){
		this.commentingUserId = commentingUserId;
	}

	public String getCommentingUserId(){
		return commentingUserId;
	}

	public void setCommentingUserName(String commentingUserName){
		this.commentingUserName = commentingUserName;
	}

	public String getCommentingUserName(){
		return commentingUserName;
	}

	public void setCommentDate(String commentDate){
		this.commentDate = commentDate;
	}

	public String getCommentDate(){
		return commentDate;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return commentId;
	}

	public void setCommentDescription(String commentDescription){
		this.commentDescription = commentDescription;
	}

	public String getCommentDescription(){
		return commentDescription;
	}

	public void setParentCommentId(String parentCommentId){
		this.parentCommentId = parentCommentId;
	}

	public String getParentCommentId(){
		return parentCommentId;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"SubCommentsItem{" + 
			"profilePicture = '" + profilePicture + '\'' + 
			",commentingUserId = '" + commentingUserId + '\'' + 
			",commentingUserName = '" + commentingUserName + '\'' + 
			",commentDate = '" + commentDate + '\'' + 
			",commentId = '" + commentId + '\'' + 
			",commentDescription = '" + commentDescription + '\'' + 
			",parentCommentId = '" + parentCommentId + '\'' + 
			",postId = '" + postId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}