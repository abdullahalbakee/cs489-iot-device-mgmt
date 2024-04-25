import React, { useContext, useState } from "react";
import Button from "react-bootstrap/button";
import Form from "react-bootstrap/Form";
import User from "../../models/user";
import { Link, useNavigate } from "react-router-dom";
import { saveToken } from "../../service/token";
import { login } from "../../service/auth";
import UserContext from "../../context/user-context";
import { setBearerToken } from "../../service/common";
import { useToast } from "react-toastify";

export default function RegistrationForm() {
  const alert = useToast();
  const [user, setUser] = useState(new User());
  const navigate = useNavigate();
  const { setAccessToken } = useContext(UserContext);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const res = await login(user);
    if (res.success) {
      const token = res.data.accessToken;
      saveToken(token);
      setAccessToken(token);
      setBearerToken(token);
      navigate("/");
    } else {
      console.log(res);
      alert.error(res.error);
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
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            name="email"
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
