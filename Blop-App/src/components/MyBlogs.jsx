import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const MyBlogs = () => {
  const [blogs, setBlogs] = useState([]);
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchBlogs();
  }, []);

  const fetchBlogs = async () => {
    try {
      const response = await axios.get("http://localhost:9090/api/v1/blogs/user", {
        headers: { Authorization: `Bearer ${token}` },
        params: {
          page: 0,
          size: 10,
        },
      });
      setBlogs(response.data.content);
    } catch (err) {
      console.error("Error fetching blogs:", err);
      setError("Failed to fetch blogs.");
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:9090/api/v1/blogs/${id}`, {
        withCredentials: true,
      });
      // setBlogs((prev) => prev.filter((blog) => blog.id !== id));
      fetchBlogs();
    } catch (err) {
      console.error("Error deleting blog:", err);
      alert("Failed to delete blog.");
    }
  };

  return (
    <div className="max-w-5xl mx-auto mt-10 px-4 w-full">
      <h2 className="text-3xl font-bold mb-6 text-center">My Blogs</h2>
      {error && <p className="text-red-500 text-center mb-4">{error}</p>}

      <div className="grid gap-6 md:grid-cols-2">
        {blogs.length > 0 ? (
          blogs.map((blog) => (
            <div key={blog.id} className="bg-white p-5 rounded shadow">
              <h3 className="text-xl font-semibold mb-2">{blog.title}</h3>
              <p className="text-gray-700 mb-4">
                {blog.content.length > 150
                  ? `${blog.content.slice(0, 150)}...`
                  : blog.content}
              </p>

              <div className="flex justify-end gap-2">
                <button
                  onClick={() => navigate(`/edit-blog/${blog.id}`)}
                  className="px-4 py-2 bg-yellow-500 text-white rounded hover:bg-yellow-600"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(blog.id)}
                  className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
                >
                  Delete
                </button>
              </div>
            </div>
          ))
        ) : (
          <div className="flex flex-col justify-center items-center w-full">
            <p className="text-center my-10">
              No Blogs Created Yet, create one here 👇
            </p>
            <Link
              to="/create-blog"
              className="bg-blue-600 text-white w-max px-4 py-2 rounded-md hover:bg-blue-700 transition-colors duration-300"
            >
              Create Blog
            </Link>
          </div>
        )}
      </div>
    </div>
  );
};

export default MyBlogs;
