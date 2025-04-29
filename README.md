# ChatApplication with springboot,JWt,spring security and websocket
<h2>Overview</h2>
<p>This project is a RESTful web application built using Spring Boot that provides a secure platform for user authentication and real-time messaging.</p>
  <h1>Project Features</h1>

  <h3>User Registration and Login</h3>
  <ul>
    <li>Users can register using a <strong>unique email</strong> and <strong>password</strong>.</li>
    <li>Login functionality is secured using <strong>JWT (JSON Web Tokens)</strong>.</li>
  </ul>

  <h3>Two-Factor Authentication (2FA)</h3>
  <ul>
    <li>On signup or password reset, users receive a <strong>One-Time Password (OTP)</strong> sent to their <strong>registered email</strong> for added security.</li>
  </ul>

  <h3>Forgot Password Feature</h3>
  <ul>
    <li>Users can reset their password by verifying the <strong>OTP</strong> sent to their <strong>email address</strong>.</li>
  </ul>

  <h3>Real-Time Chat Functionality</h3>
  <ul>
    <li><strong>Public Chatroom:</strong> Users can send and receive messages in a <strong>group chat</strong>.</li>
    <li><strong>Private Messaging:</strong> Users can chat <strong>privately</strong> with specific users based on their <strong>email IDs</strong>.</li>
    <li><strong>Chat History:</strong> All <strong>public and private conversations</strong> are stored and retrievable.</li>
  </ul>

  <h3>Logout Functionality</h3>
  <ul>
    <li><strong>JWT tokens</strong> are invalidated upon logout to ensure <strong>secure session management</strong>.</li>
  </ul>

  <h3>Security</h3>
  <ul>
    <li><strong>JWT Authentication</strong> is used to secure <strong>APIs</strong>.</li>
    <li><strong>Spring Security</strong> is implemented for <strong>authorization control</strong> across endpoints.</li>
  </ul>
  <h2> Technologies Used</h3>
  <ul>
    <li>Java 17+</li>
    <li>Spring Boot</li>
      <li>Spring Security</li>
      <li>WebSocket (for real-time communication)</li>
      <li>JWT (JSON Web Tokens)</li>
      <li>Email API (for OTP delivery)</li>
     <li>MySQL / H2 Database (for storing user data and messages)
</li>
  </ul>
  <hr>
  <h2>GetStarted</h2>
  <h3>Prerequisites</h3>
<p>Before you begin, ensure you have the following installed:</p>
<ul>
    <li><strong>Java 11+</strong></li>
    <li><strong>Maven</strong></li>
    <li><strong>MySQL</strong> (or any relational database)</li>
</ul>

<h3>Installation and Setup</h3>
<ol>
    <li>Clone the repository:
        <pre><code>git clone https://github.com/iqra-jyothi/ChatApplication.gitt</code></pre>
    </li>
    <li>Navigate to the project directory:
        <pre><code>cd practiceprojectformList</code></pre>
    </li>
    <li>Open <strong>src/main/resources/application.properties</strong> and configure your database:
        <pre><code>
spring.datasource.url=jdbc:mysql://localhost:3306/copy
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
jwt.secret=mysecretkey
        </code></pre>
           </li>
    <li>Create the database in MySQL:
        <pre><code>CREATE DATABASE copy;</code></pre>
    </li>
    <li>Build and run the application:
        <pre><code>mvn spring-boot:run</code></pre>
    </li>
</ol>

<h3>Running the Application</h3>
<p>Once the application is running, it will be available on <strong>http://localhost:</s9092trong>.</p>

<h4>API Endpoints</h4>
<ul>
    <li><strong>POST</strong> <code>/register</code> - Register a new user.</li>
    <li><strong>POST</strong> <code>/login</code> - Authenticate a user and get a JWT.</li>
    <li><strong>POST</strong> <code>api/auth/forgot-password</code> - if user forget there password.</li>
    <li><strong>POST</strong> <code>/api/auth/reset-password</code> - reset the password for authenticated user and allow to set new password</li>
    <li><strong>POT</strong> <code>/api/auth/verify-otp</code> - verify otp that are generated on specific email</li>
       <li><strong>POST</strong> <code>/api/user/updateprofile</code> - to set profile it require JWT authenticated token</li>
  <li><strong>GET</strong> <code>/api/user/all</code> - get list of uers.</li>
    <li><strong>GET</strong> <code>/api/message</code> - get chat history</li>
      <li><strong>GET</strong> <code>/api/message/private/${currentUserEmail}/${selectedUser.email}</code> - Get history of private messages</li>
</ul>
<h3>Sample JSON for Registration</h3>
<pre><code>
  {
    "name":"test@gmail.com",
    "password":"123456"
}
</code></pre>
<h3>Sample JSON for Login</h3>
<pre><code>
  {
    "name":"test@gmail.com",
    "password":"123456"
}
</code></pre>
<h3>Sample JSON for forget password</h3>
<pre><code>
  {
    "name":"test@gmail.com",
  
}
</code></pre>

<h3>Sample JSON for Verify Password</h3>
<pre><code>
{
    "email":"test@gmail.com",
    "otp":"252186"
}
</code></pre>


<h3>Sample JSON for Reset-Password</h3>
<pre><code>
{  
    "email":"test@gmail.com",
    "otp":"741383",
  "newPassword": "newSecurePassword"
}
</code></pre>
<h3>Sample JSON for profile</h3>
<pre><code>
{  
name:"test",
  profile:"optional"
}
</code></pre>
<h2>Authentication with JWT</h2>
<p>When a user successfully logs in using the /auth/login endpoint, the server responds with a JWT. This token should be included in the header of every subsequent request to secure endpoints (e.g., sending or fetching messages).</p>
<p>For example, add the following header:
</p>
<pre><code>Authorization: Bearer your-jwt-token</code></pre>
<h2>Architecture</h2>
<p>The chat application follows a layered architecture:</p>
<ul>
    <li><strong>Controller Layer:</strong> Handles HTTP requests and responses.</li>
    <li><strong>Service Layer:</strong> Contains the business logic and interacts with repositories.</li>
    <li><strong>Repository Layer:</strong> Manages data persistence using JPA and interacts with the database.</li>
    <li><strong>DTO (Data Transfer Objects):</strong> Used to transfer data between client and server without exposing internal database models.</li>
    <li><strong>Component:</strong> Defines Spring-managed reusable classes for business logic or helper utilities.</li>
    <li><strong>Entity:</strong> Represents database tables in Java code using JPA annotations.</li>
</ul>

<h2>Database Schema</h2>
<p>The application uses three tables:</p>
<ul>
    <li><strong>Users:</strong>
        <ul>
            <li><strong>id:</strong> Auto-generated user ID.</li>
            <li><strong>name:</strong> name has email.</li>
            <li><strong>password:</strong> Encrypted user password.</li>
        </ul>
    </li>
    <li><strong>Chat Messages:</strong>
        <ul>
            <li><strong>id:</strong> Auto-generated message ID.</li>
            <li><strong>content:</strong> Text content of the message.</li>
            <li><strong>sender_id:</strong> ID of the user who sent the message.</li>
            <li><strong>receiver_id:</strong> ID of the user who received the message (null for public messages).</li>
            <li><strong>timestamp:</strong> When the message was sent.</li>
            <li><strong>message_type:</strong> Indicates whether the message is public or private.</li>
        </ul>
    </li>
    <li><strong>User Profiles:</strong>
        <ul>
            <li><strong>user_id:</strong> Associated user ID.</li>
            <li><strong>profile_photo_url:</strong> URL to profile picture.</li>
            <li><strong>name</strong> name of the user.</li>
        </ul>
    </li>
</ul>

<h2>Security</h2>
<p>The application uses Spring Security to secure user registration, login, and messaging functionalities:</p>
<ul>
    <li>All endpoints, except for <code>/auth/register</code> and <code>/auth/login</code>, are secured and require a valid JWT token.</li>
    <li>Passwords are stored in an encrypted format using <strong>BCrypt</strong>.</li>
    <li>JWT tokens are used to authorize users for accessing protected endpoints.</li>
</ul>






