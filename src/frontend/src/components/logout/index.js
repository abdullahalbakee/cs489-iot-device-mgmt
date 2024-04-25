import { useContext, useEffect } from "react";
import UserContext from "../../context/user-context";
import { removeToken } from "../../service/token";
import { useNavigate } from "react-router-dom";
import { removeBearerToken } from "../../service/common";

export default function () {
  const navigate = useNavigate();
  const { setAccessToken } = useContext(UserContext);

  const logout = () => {
    removeToken();
    setAccessToken(null);
    removeBearerToken();
    navigate("/");
  };

  useEffect(logout, []);

  return <></>;
}
