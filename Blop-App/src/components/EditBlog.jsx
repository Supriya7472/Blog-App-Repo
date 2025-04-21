import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const EditBlog = () => {
  const { blogId } = useParams(); // Get blog ID from URL
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const [formData, setFormData] = useState({
    title: "",
    content: "",
  });

  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBlog = async () => {
      try {
        const res = await axios.get(`http://localhost:9090/api/v1/blogs/${blogId}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setFormData({ title: res.data.title, content: res.data.content });
      } catch (err) {
        console.error("Error fetching blog:", err);
        setError("Failed to load blog.");
      }
    };
    fetchBlog();
  }, [blogId, token]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    const newErrors = {};
    if (!formData.title || formData.title.length < 5 || formData.title.length > 100) {
      newErrors.title = "Title must be between 5 and 100 characters";
    }
    if (!formData.content || formData.content.length < 20) {
      newErrors.content = "Content must be at least 20 characters long";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      await axios.put(`http://localhost:9090/api/v1/blogs/${blogId}`, formData, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      setSuccess("Blog updated successfully!");
      setTimeout(() => navigate("/myblogs"), 1500);
    } catch (err) {
      console.error("Update failed:", err);
      setError(err.response?.data?.message || "Failed to update blog");
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100 px-4">
      <div className="bg-white p-8 rounded shadow-md w-full max-w-2xl">
        <h2 className="text-2xl font-bold mb-6 text-center">Edit Blog</h2>

        {error && <p className="bg-red-100 text-red-700 p-2 rounded mb-4">{error}</p>}
        {success && <p className="bg-green-100 text-green-700 p-2 rounded mb-4">{success}</p>}

        <form onSubmit={handleUpdate} className="space-y-4">
          <div>
            <input
              type="text"
              name="title"
              placeholder="Blog Title"
              value={formData.title}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded"
            />
            {errors.title && <p className="text-red-500 text-sm">{errors.title}</p>}
          </div>

          <div>
            <textarea
              name="content"
              placeholder="Blog Content"
              rows="8"
              value={formData.content}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded"
            />
            {errors.content && <p className="text-red-500 text-sm">{errors.content}</p>}
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
          >
            Update Blog
          </button>
        </form>
      </div>
    </div>
  );
};

export default EditBlog;
