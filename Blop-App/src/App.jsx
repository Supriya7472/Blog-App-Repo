import React from "react";
import { Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import BlogList from "./components/BlogList";
import Login from "./components/Login";
import CreateBlog from "./components/CreateBlog";
import MyBlogs from "./components/MyBlogs";
import UpdateUser from "./components/UpdateUser";
import EditBlog from "./components/EditBlog";
import Navbar from "./components/NavBar";
import NotFoundPage from "./components/NotFoundPage";
import { useAuth } from "./context/AuthContext";
import BlogDetail from "./components/BlogDetail";

function App() {
  const { authenticated } = useAuth();

  return (
    <>
      <Navbar />
      <div className="bg-gray-100 min-h-screen p-4">
        {authenticated ? (
          <Routes>
            <Route path="/" element={<BlogList />} />
            <Route path="/create-blog" element={<CreateBlog />} />
            <Route path="/my-blogs" element={<MyBlogs />} />
            <Route path="/blog-detail/:blogId" element={<BlogDetail />} />
            <Route path="/update-profile" element={<UpdateUser />} />
            <Route path="/edit-blog/:blogId" element={<EditBlog />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        ) : (
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path="/" element={<BlogList />} />
            <Route path="*" element={<NotFoundPage />} />
          </Routes>
        )}
      </div>
    </>
  );
}

export default App;
