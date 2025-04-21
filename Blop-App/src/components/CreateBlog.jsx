import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CreateBlog = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!title || !content) {
      setError("Both title and content are required.");
      setSuccess("");
      return;
    }

    try {
      const token = localStorage.getItem("token");

      await axios.post(
        "http://localhost:9090/api/v1/blogs",
        { title, content },
        {
          withCredentials: true,
        }
      );

      setSuccess("Blog posted successfully!");
      setError("");
      setTitle("");
      setContent("");

      setTimeout(() => navigate("/my-blogs"), 2000);
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || "Failed to post blog.");
      setSuccess("");
    }
  };

  return (
    <div className="max-w-3xl mx-auto mt-10 bg-white shadow p-6 rounded-md">
      <h2 className="text-2xl font-bold mb-6">Create a New Blog</h2>

      {error && <p className="text-red-600 mb-4">{error}</p>}
      {success && <p className="text-green-600 mb-4">{success}</p>}

      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="w-full border p-3 rounded"
        />

        <textarea
          rows="6"
          placeholder="Write your blog content here..."
          value={content}
          onChange={(e) => setContent(e.target.value)}
          className="w-full border p-3 rounded"
        ></textarea>

        <button
          type="submit"
          className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
        >
          Post Blog
        </button>
      </form>
    </div>
  );
};

export default CreateBlog;
