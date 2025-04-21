import React from "react";
import { useNavigate } from "react-router-dom";

const NotFoundPage = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 px-4">
      <div className="max-w-xl text-center flex flex-col items-center justify-center">
        <h1 className="text-6xl font-bold text-gray-800 mb-4">404</h1>
        <p className="text-xl text-gray-600 mb-2">Oops! Page not found.</p>
        <p className="text-md text-gray-500 mb-6">
          The page you're looking for doesn't exist or has been moved.
        </p>
        <button
          className="flex items-center gap-2 text-white bg-black hover:bg-gray-800 rounded-xl px-4 py-2"
          onClick={() => navigate("/")}
        >
          Back to Home
        </button>
      </div>
    </div>
  );
};

export default NotFoundPage;
