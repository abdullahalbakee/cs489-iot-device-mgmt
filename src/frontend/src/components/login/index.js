import React, { useContext, useState } from "react";
import Button from "react-bootstrap/button";
import Form from "react-bootstrap/Form";
import User from "../../models/user";
import { Link, useNavigate } from "react-router-dom";
import { saveToken } from "../../service/token";
import { login } from "../../service/auth";
import UserContext from "../../context/user-context";
import { setBearerToken } from "../../service/common";
import { toast, Zoom } from "react-toastify";

export default function LoginForm() {
  const [user, setUser] = useState(new User());
  const navigate = useNavigate();
  const { setAccessToken } = useContext(UserContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await login(user);
      const token = res.token;
      saveToken(token);
      setAccessToken(token);
      setBearerToken(token);
      navigate("/");
    } catch(e) {
      console.log("Login error", e);
      toast.error(e.response.data, {position: "top-center", transition:Zoom});
    }
  };

  const handleTextChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  return (
    <>
      <Form
        className="col-12 col-sm-6 col-md-3 mx-auto"
        onSubmit={handleSubmit}
      >
        <Form.Group className="mb-3" controlId="registrationEmail">
          <Form.Label>User name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Username"
            name="username"
            onChange={handleTextChange}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="registrationPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            name="password"
            onChange={handleTextChange}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Login
        </Button>
        <p>
          To register <Link to="/register">click here</Link>
        </p>
      </Form>
    </>
  );
}
