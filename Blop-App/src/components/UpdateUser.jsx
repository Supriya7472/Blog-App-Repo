import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const UpdateUser = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const [formData, setFormData] = useState({
    userName: "",
    phno: "",
    email: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [errors, setErrors] = useState({});

  // Optional: Fetch user details to pre-fill form (only if backend supports it)
  const fetchUserDetails = async () => {
    try {
      const res = await axios.get("http://localhost:9090/api/v1/account", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setFormData(res.data);
    } catch (err) {
      console.error("Failed to fetch user data", err);
    }
  };

  useEffect(() => {
    fetchUserDetails();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.userName.match(/^[A-Za-z ]{2,30}$/)) {
      newErrors.userName = "Name must be 2-30 letters or spaces.";
    }
    if (!formData.phno.match(/^[6-9]\d{9}$/)) {
      newErrors.phno = "Invalid phone number.";
    }
    if (!formData.email.match(/^[a-zA-Z0-9._%+-]+@gmail\.com$/)) {
      newErrors.email = "Enter valid Gmail address.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      await axios.put("http://localhost:9090/api/v1/users", formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      });

      setSuccess("User info updated successfully.");
      setError("");
      setTimeout(() => navigate("/"), 2000);
    } catch (err) {
      console.error("Update error:", err);
      setError(err.response?.data?.message || "Update failed.");
      setSuccess("");
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <div className="bg-white p-8 rounded shadow w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">Update Profile</h2>

        {error && <p className="bg-red-100 text-red-700 p-2 rounded mb-4">{error}</p>}
        {success && <p className="bg-green-100 text-green-700 p-2 rounded mb-4">{success}</p>}

        <form onSubmit={handleUpdate} className="space-y-4">
          <div>
            <input
              type="text"
              name="userName"
              placeholder="Full Name"
              value={formData.userName}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded"
            />
            {errors.userName && <p className="text-red-500 text-sm">{errors.userName}</p>}
          </div>

          <div>
            <input
              type="text"
              name="phno"
              placeholder="Phone Number"
              value={formData.phno}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded"
            />
            {errors.phno && <p className="text-red-500 text-sm">{errors.phno}</p>}
          </div>

          <div>
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-4 py-2 border rounded"
            />
            {errors.email && <p className="text-red-500 text-sm">{errors.email}</p>}
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
          >
            Update
          </button>
        </form>
      </div>
    </div>
  );
};

export default UpdateUser;
