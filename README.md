# 📝 Full Stack Blog Application

A complete full stack **Blog Application** built using **Spring Boot (backend)** and **React with Vite (frontend)**. This application allows users to register, log in, create, update, and delete blogs securely.

---

## 🔍 Features

### ✅ Authentication & Authorization
- JWT-based authentication using **Access Token** and **Refresh Token**
- Spring Security integrated in backend
- Only **logged-in users** can create blogs
- Users can only **edit/delete their own blogs**

### ✅ Blog Operations
- List all blogs (paginated)
- View complete blog content
- Create new blog
- Update or delete blog (only for blog creators)

### ✅ Frontend (React + Vite)
- Simple and clean UI using **TailwindCSS**
- Dynamic routing with `react-router-dom`
- Registration & Login pages with field-level validation
- Custom alert system (no toast used)

### ✅ Backend (Spring Boot)
- RESTful API endpoints for blog and auth
- Secure JWT-based login
- Blog ownership enforced before update/delete
- Global access to config using `AppEnv.java`

---

## 💡 Tech Stack

| Category     | Tools Used                                   |
|--------------|----------------------------------------------|
| **Frontend** | React, Vite, Tailwind CSS, Axios, React Router |
| **Backend**  | Java, Spring Boot, Spring Security, JPA, MySQL |
| **JWT Auth** | Access Token & Refresh Token implementation |
| **Build Tool** | Maven                                      |
| **Database** | MySQL                                        |
| **Others**   | Git, GitHub, Postman (for testing)           |

---

## 🗂 Project Structure

```
blog-application/
├── backend/
│   ├── src/
│   ├── pom.xml
│   ├── application.yml (contains placeholders)
│   └── AppEnv.java (used for global config access)
├── frontend/
│   ├── src/
│   ├── vite.config.js
│   └── .env.example (for production team, values to set)
├── README.md
```

---

## ⚙️ Environment Configuration

### 📌 Backend
- `application.yml` includes placeholders for database and secret keys.
- Actual values are kept in a `.env` file (not pushed to GitHub).
- Example environment setup is given in `.env.example`.

### 📌 Frontend
- React environment variables are stored in `.env`.
- Make sure to update API base URLs if needed.

---

## 🚀 Getting Started

### ✅ Prerequisites
- Java 17+
- Node.js & npm
- MySQL Server
- Maven

### 🔧 Backend Setup

```bash
cd backend
# Configure your MySQL credentials and JWT secrets in the .env file
./mvnw spring-boot:run
```

### 💻 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

---

## 🔒 Security Notes

- JWT is stored in HttpOnly cookies for secure auth handling.
- Backend APIs are protected and require authentication.
- Only blog owners can update or delete their blogs.

---

## 👩‍💻 Developed By

**Supriya** — Java Full Stack Developer  
GitHub: [Supriya7472](https://github.com/Supriya7472)

---

## 📄 License

This project is open-source and available under the MIT License.
