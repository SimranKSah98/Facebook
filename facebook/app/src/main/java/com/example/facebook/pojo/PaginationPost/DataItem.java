package com.example.facebook.pojo.PaginationPost;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("isBusinessProfilePost")
	private Object isBusinessProfilePost;

	@SerializedName("reaction")
	private Reaction reaction;

	@SerializedName("counterOfDislilkes")
	private int counterOfDislilkes;

	@SerializedName("postVideoUrl")
	private Object postVideoUrl;

	@SerializedName("postId")
	private String postId;

	@SerializedName("postImageUrl")
	private String postImageUrl;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

	@SerializedName("profilePicture")
	private String profilePicture;

	@SerializedName("counterOfComments")
	private int counterOfComments;

	@SerializedName("postDate")
	private String postDate;

	@SerializedName("parentComments")
	private List<ParentCommentsItem> parentComments;

	@SerializedName("counterOfEmojis")
	private int counterOfEmojis;

	@SerializedName("counterOfLikes")
	private int counterOfLikes;

	@SerializedName("postDescription")
	private String postDescription;

	public void setIsBusinessProfilePost(Object isBusinessProfilePost){
		this.isBusinessProfilePost = isBusinessProfilePost;
	}

	public Object getIsBusinessProfilePost(){
		return isBusinessProfilePost;
	}

	public void setReaction(Reaction reaction){
		this.reaction = reaction;
	}

	public Reaction getReaction(){
		return reaction;
	}

	public void setCounterOfDislilkes(int counterOfDislilkes){
		this.counterOfDislilkes = counterOfDislilkes;
	}

	public int getCounterOfDislilkes(){
		return counterOfDislilkes;
	}

	public void setPostVideoUrl(Object postVideoUrl){
		this.postVideoUrl = postVideoUrl;
	}

	public Object getPostVideoUrl(){
		return postVideoUrl;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setPostImageUrl(String postImageUrl){
		this.postImageUrl = postImageUrl;
	}

	public String getPostImageUrl(){
		return postImageUrl;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setProfilePicture(String profilePicture){
		this.profilePicture = profilePicture;
	}

	public String getProfilePicture(){
		return profilePicture;
	}

	public void setCounterOfComments(int counterOfComments){
		this.counterOfComments = counterOfComments;
	}

	public int getCounterOfComments(){
		return counterOfComments;
	}

	public void setPostDate(String postDate){
		this.postDate = postDate;
	}

	public String getPostDate(){
		return postDate;
	}

	public void setParentComments(List<ParentCommentsItem> parentComments){
		this.parentComments = parentComments;
	}

	public List<ParentCommentsItem> getParentComments(){
		return parentComments;
	}

	public void setCounterOfEmojis(int counterOfEmojis){
		this.counterOfEmojis = counterOfEmojis;
	}

	public int getCounterOfEmojis(){
		return counterOfEmojis;
	}

	public void setCounterOfLikes(int counterOfLikes){
		this.counterOfLikes = counterOfLikes;
	}

	public int getCounterOfLikes(){
		return counterOfLikes;
	}

	public void setPostDescription(String postDescription){
		this.postDescription = postDescription;
	}

	public String getPostDescription(){
		return postDescription;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"isBusinessProfilePost = '" + isBusinessProfilePost + '\'' + 
			",reaction = '" + reaction + '\'' + 
			",counterOfDislilkes = '" + counterOfDislilkes + '\'' + 
			",postVideoUrl = '" + postVideoUrl + '\'' + 
			",postId = '" + postId + '\'' + 
			",postImageUrl = '" + postImageUrl + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			",profilePicture = '" + profilePicture + '\'' + 
			",counterOfComments = '" + counterOfComments + '\'' + 
			",postDate = '" + postDate + '\'' + 
			",parentComments = '" + parentComments + '\'' + 
			",counterOfEmojis = '" + counterOfEmojis + '\'' + 
			",counterOfLikes = '" + counterOfLikes + '\'' + 
			",postDescription = '" + postDescription + '\'' + 
			"}";
		}
}