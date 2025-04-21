export default function parseAuthDetails(serverResponse) {
    const { userId, username, email, accessExpiration, refreshExpiration } = serverResponse.data;
    return {
      userId,
      username,
      email,
      accessExpiration: new Date(accessExpiration),
      refreshExpiration: new Date(refreshExpiration),
    };
  }  