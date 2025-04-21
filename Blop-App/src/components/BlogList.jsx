import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from "./NavBar";

const BlogList = () => {
  const [blogs, setBlogs] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const navigate = useNavigate();
  const fetchBlogs = async (page = 0) => {
    setLoading(true);
    try {
      const res = await axios.get(
        `http://localhost:9090/api/v1/blogs?page=${page}&size=10`,
        {
          withCredentials: true,
        }
      );
      setBlogs(res.data.content);
      setTotalPages(res.data.totalPages); // Get total number of pages
      setCurrentPage(page); // Update current page
    } catch (err) {
      console.error("Error fetching blogs:", err);
      setError("Failed to fetch blogs");
    } finally {
      setLoading(false); // Stop loading after fetching
    }
  };

  useEffect(() => {
    fetchBlogs(currentPage);
  }, [currentPage]);

  const handleRegister = async () => {
    navigate("/register");
  };

  async () => {
    try {
      await axios.post(
        "http://localhost:9090/api/v1/login",
        {},
        { withCredentials: true }
      );
      alert("Login successful");
    } catch (err) {
      console.error("Login failed:", err);
      alert("Login failed");
    }
  };

  return (
    <div className="p-4">
      {error && <p className="text-red-500">{error}</p>}
      <h1 className="text-4xl font-bold text-center mb-8">Blog Application</h1>
      {/* Show loading spinner */}
      {loading ? (
        <div className="spinner">Loading...</div>
      ) : (
        <>
          {blogs.length === 0 ? (
            <p>No blogs found.</p>
          ) : (
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
              {blogs.map((blog) => (
                <div
                  key={blog.blogId}
                  onClick={() => navigate(`/blog-detail/${blog.blogId}`)}
                  className="cursor-pointer bg-white p-6 shadow-lg rounded-lg border border-gray-300 hover:shadow-xl transition-shadow"
                >
                  <h3 className="text-xl font-semibold mb-2">{blog.title}</h3>
                  <p className="text-gray-600 mb-4 line-clamp-2 overflow-hidden text-ellipsis">
                    {blog.content}
                  </p>
                  <p className="text-sm text-gray-400">
                    Posted by {blog.createdBy}
                  </p>
                  <p className="text-sm text-gray-500">Status: {blog.status}</p>
                </div>
              ))}
            </div>
          )}

          {/* Pagination */}
          <div className="flex justify-between mt-8">
            <button
              onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 0))}
              disabled={currentPage === 0}
              className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700 disabled:opacity-50"
            >
              Previous
            </button>
            <button
              onClick={() =>
                setCurrentPage((prev) => Math.min(prev + 1, totalPages - 1))
              }
              disabled={currentPage === totalPages - 1}
              className="bg-blue-600 text-white px-6 py-2 rounded-md hover:bg-blue-700 disabled:opacity-50"
            >
              Next
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default BlogList;
