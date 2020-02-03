package com.example.facebook.pojo.PaginationPost;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ParentCommentsItem{

	@SerializedName("profilePicture")
	private Object profilePicture;

	@SerializedName("commentingUserId")
	private String commentingUserId;

	@SerializedName("subComments")
	private List<SubCommentsItem> subComments;

	@SerializedName("commentingUserName")
	private String commentingUserName;

	@SerializedName("commentDate")
	private String commentDate;

	@SerializedName("commentId")
	private String commentId;

	@SerializedName("commentDescription")
	private String commentDescription;

	@SerializedName("parentCommentId")
	private Object parentCommentId;

	@SerializedName("postId")
	private String postId;

	public void setProfilePicture(Object profilePicture){
		this.profilePicture = profilePicture;
	}

	public Object getProfilePicture(){
		return profilePicture;
	}

	public void setCommentingUserId(String commentingUserId){
		this.commentingUserId = commentingUserId;
	}

	public String getCommentingUserId(){
		return commentingUserId;
	}

	public void setSubComments(List<SubCommentsItem> subComments){
		this.subComments = subComments;
	}

	public List<SubCommentsItem> getSubComments(){
		return subComments;
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

	public void setParentCommentId(Object parentCommentId){
		this.parentCommentId = parentCommentId;
	}

	public Object getParentCommentId(){
		return parentCommentId;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	@Override
 	public String toString(){
		return 
			"ParentCommentsItem{" + 
			"profilePicture = '" + profilePicture + '\'' + 
			",commentingUserId = '" + commentingUserId + '\'' + 
			",subComments = '" + subComments + '\'' + 
			",commentingUserName = '" + commentingUserName + '\'' + 
			",commentDate = '" + commentDate + '\'' + 
			",commentId = '" + commentId + '\'' + 
			",commentDescription = '" + commentDescription + '\'' + 
			",parentCommentId = '" + parentCommentId + '\'' + 
			",postId = '" + postId + '\'' + 
			"}";
		}
}