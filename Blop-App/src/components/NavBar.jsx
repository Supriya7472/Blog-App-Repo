import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Navbar = () => {
  const navigate = useNavigate();
  const { authenticated, logout } = useAuth();

  return (
    <nav className="bg-blue-600 text-white px-6 py-4 shadow-md">
      <div className="container mx-auto flex justify-between items-center">
        <h1 className="text-xl font-bold">
          <Link to="/">Blogs</Link>
        </h1>

        <div className="space-x-4">
          {!authenticated ? (
            <>
              <Link
                to="/login"
                className="hover:bg-blue-700 px-4 py-2 rounded transition"
              >
                Login
              </Link>
              <Link
                to="/register"
                className="hover:bg-blue-700 px-4 py-2 rounded transition"
              >
                Register
              </Link>
            </>
          ) : (
            <>
              <Link
                to="/my-blogs"
                className="hover:bg-blue-700 px-4 py-2 rounded transition"
              >
                My Blogs
              </Link>
              <Link
                to="/create-blog"
                className="hover:bg-blue-700 px-4 py-2 rounded transition"
              >
                Create Blog
              </Link>
              <button
                onClick={logout}
                className="hover:bg-blue-700 px-4 py-2 rounded transition"
              >
                Logout
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
