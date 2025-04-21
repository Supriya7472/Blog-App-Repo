import React, { createContext, useState, useEffect, useContext } from "react";
import fetchAuthDetails from "../auth/FetchAuthDetails";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const authContext = createContext();

export const useAuth = () => useContext(authContext);

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState({
    userId: undefined,
    username: undefined,
    email: undefined,
    accessExpiration: undefined,
    refreshExpiration: undefined,
  });
  const [authenticated, setAuthenticated] = useState(false);
  const navigate = useNavigate();

  const updateAuthState = (user) => {
    const { userId, username, email, accessExpiration, refreshExpiration } =
      user;

    // Only update if none are null or undefined
    if (userId && username && email && accessExpiration && refreshExpiration) {
      setUser({
        userId: userId,
        username: username,
        email,
        accessExpiration,
        refreshExpiration,
      });
      setAuthenticated(true);
    }
  };

  const fromServer = async () => {
    const user = await fetchAuthDetails(setAuthenticated, setUser);
    updateAuthState(user);
  };

  useEffect(() => {
    fromServer();
  }, []);

  useEffect(() => {
    if (
      user.userId &&
      user.email &&
      user.username &&
      user.accessExpiration &&
      user.refreshExpiration
    ) {
      localStorage.setItem("user", JSON.stringify(user));
    }
  }, [user]);

  const logout = async () => {
    const response = await axios.post(
      "http://localhost:9090/api/v1/logout",
      {},
      {
        withCredentials: true,
      }
    );

    if (response.data) {
      const unAuthUser = {
        userId: undefined,
        username: undefined,
        email: undefined,
        accessExpiration: undefined,
        refreshExpiration: undefined,
      };
      setAuthenticated(false);
      setUser(unAuthUser);
      localStorage.removeItem("user");

      navigate("/");
    }
  };

  const value = {
    user,
    setUser,
    authenticated,
    setAuthenticated,
    logout,
  };

  return <authContext.Provider value={value}>{children}</authContext.Provider>;
};

export default AuthProvider;
