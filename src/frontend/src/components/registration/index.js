import React, { useState } from "react";
import Button from "react-bootstrap/button";
import Form from "react-bootstrap/Form";
import User from "../../models/user";
import { register } from "../../service/auth";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

export default function RegistrationForm() {
  console.log(alert);
  const [user, setUser] = useState(new User());
  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (user.password !== user.retypePassword) {
      alert.error("Password does not match with Retype Password");
      return;
    }

    const res = await register(user);
    if (res.success) {
      alert.success(
        "Registration was successful! You can now log into the system."
      );
      navigate("/");
    } else {
      console.log(res);
      toast(res.error);
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
          <Form.Label>Username</Form.Label>
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

        <Form.Group className="mb-3" controlId="registrationRetypePassword">
          <Form.Label>Retype Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Retype Password"
            name="retypePassword"
            onChange={handleTextChange}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Register
        </Button>
        <p>
          Already have an account? Login <Link to="/login">here</Link>
        </p>
      </Form>
    </>
  );
}
