# Twitter-like Social Media Backend

## Project Overview

This project implements the backend for a Twitter-like social media platform. Users can register, post messages, follow others, and interact with posts through likes and replies. The project does not include a frontend and focuses entirely on backend functionalities.

## Features

User Management

- Register: Users can register with a unique username, first name, last name, email, and password.

- Search: Users can search for other users by username, first name, or last name.

- Unregister: Users can delete their account, which removes all their posts.

Social Interactions

- Follow: Users can follow other users to receive their public posts.

- Unfollow: Users can unfollow a user to stop receiving their posts.

Posts

- Add Post: Users can create public posts.

- Get Own Posts: Users can retrieve all posts they have created, with optional filtering for posts newer than a specific timestamp.

- Get Feed: Users can retrieve all posts from users they follow.

- Delete Post: Users can delete their own posts. This will also remove all likes and replies associated with the post.

- Repost: Users can copy an existing post from another user.

- Get Mentions: Users can retrieve all posts in which they were mentioned.

Likes

- Like Post: Users can like an existing post. The post owner will see a list of all likes on their posts.

- Remove Like: Users can remove their like from a post.

- Cascade Deletion: When a post is deleted, all associated likes are removed.

Replies

- Add Post Reply: Users can reply to posts or other replies. Replies can be public or private (visible only to the original post owner).

- Cascade Deletion: When a parent post is deleted, all its replies are removed.

### This project was made as part of Fii Practic 2024.
