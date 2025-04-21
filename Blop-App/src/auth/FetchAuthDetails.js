import axios from "axios";
import parseAuthDetails from "./ParseAuthDetails";

export default async function fetchAuthDetails(setAuthenticated, setUser) {
  const storedUser = localStorage.getItem("user");

  if (storedUser) {
    const parsedUser = JSON.parse(storedUser);

    if (parsedUser) return parsedUser;
  }

  console.log("making api call..");
  try {
    const response = await axios.get("http://localhost:9090/api/v1/auth", {
      withCredentials: true,
    });

    if (response.status !== 200) {
      throw new Error("Failed to fetch auth record");
    }

    const user = parseAuthDetails(response.data);

    return user;
  } catch (error) {
    console.log("Error fetching auth record:", error);
  }
}
