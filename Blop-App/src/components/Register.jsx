import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const Register = () => {
  const navigate = useNavigate();

  // Form data and error/success state
  const [formData, setFormData] = useState({
    userName: "",
    phno: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");

  // Handle form data changes
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Simple client-side validation
  const validateForm = () => {
    const newErrors = {};
    if (!formData.userName) newErrors.userName = "User name is required.";
    if (!formData.phno.match(/^[6-9]\d{9}$/)) newErrors.phno = "Invalid phone number.";
    if (!formData.email.match(/^[a-zA-Z0-9._%+-]+@gmail\.com$/)) newErrors.email = "Invalid email address.";
    if (!formData.password) newErrors.password = "Password is required.";
    if (formData.password !== formData.confirmPassword) newErrors.confirmPassword = "Passwords do not match.";
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    console.log("Form Data Submitted:", formData); // Log the data being sent

    try {
       await axios.post("http://localhost:9090/api/v1/register", formData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      setSuccess("Registration successful!");
      setError("");
      setTimeout(() => {
        navigate("/login"); // Redirect to login after successful registration
      }, 2000);
    } catch (err) {
      console.error("Error occurred during registration:", err);

      // Log the complete error message for debugging
      console.log("Error response:", err.response);

      // Check if the error response has a message and display it
      setError(err.response?.data?.message || "Registration failed.");
      setSuccess("");
    }
  };

  return (
    <div className="flex h-screen items-center justify-center bg-gray-100">
      <div className="w-full max-w-md p-8 bg-white shadow-md rounded-md">
        <h2 className="text-2xl font-bold mb-6 text-center">Register</h2>

        {/* Display success/error messages */}
        {error && <p className="bg-red-100 text-red-700 p-2 mb-4 rounded">{error}</p>}
        {success && <p className="bg-green-100 text-green-700 p-2 mb-4 rounded">{success}</p>}

        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="userName"
            placeholder="Full Name"
            value={formData.userName}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded"
          />
          {errors.userName && <p className="text-red-500 text-sm">{errors.userName}</p>}

          <input
            type="text"
            name="phno"
            placeholder="Phone Number"
            value={formData.phno}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded"
          />
          {errors.phno && <p className="text-red-500 text-sm">{errors.phno}</p>}

          <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded"
          />
          {errors.email && <p className="text-red-500 text-sm">{errors.email}</p>}

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded"
          />
          {errors.password && <p className="text-red-500 text-sm">{errors.password}</p>}

          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={formData.confirmPassword}
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded"
          />
          {errors.confirmPassword && <p className="text-red-500 text-sm">{errors.confirmPassword}</p>}

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700"
          >
            Register
          </button>
        </form>

        <p className="mt-4 text-center text-sm">
          Already have an account?{" "}
          <Link to="/login" className="text-blue-600 hover:underline">
            Login here
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Register;
