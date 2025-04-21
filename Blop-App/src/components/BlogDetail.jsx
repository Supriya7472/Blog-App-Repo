import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";

const BlogDetail = () => {
  const { blogId } = useParams(); // Get blogId from URL
  const [blog, setBlog] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBlog = async () => {
      try {
        const res = await axios.get(
          `http://localhost:9090/api/v1/blogs/${blogId}`,
          {
            withCredentials: true,
          }
        );
        console.log(res);
        setBlog(res.data.data);
      } catch (err) {
        console.error("Error fetching blog:", err);
        setError("Failed to load blog");
      } finally {
        setLoading(false);
      }
    };

    fetchBlog();
  }, [blogId]);

  if (loading) return <p className="text-center mt-10">Loading...</p>;
  if (error) return <p className="text-center text-red-500 mt-10">{error}</p>;

  return (
    <div className="max-w-3xl mx-auto px-4 py-8 bg-white shadow-md rounded-lg">
      <h1 className="text-3xl font-bold mb-4">{blog.title}</h1>
      <p className="text-sm text-gray-500 mb-2">Posted by {blog.createdBy}</p>
      <p className="text-sm text-gray-500 mb-4">Status: {blog.status}</p>
      <div className="text-gray-800 whitespace-pre-line">{blog.content}</div>
    </div>
  );
};

export default BlogDetail;
