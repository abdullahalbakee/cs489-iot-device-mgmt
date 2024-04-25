import { useContext } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import UserContext from "../../context/user-context";

export default function () {
  const { accessToken } = useContext(UserContext);

  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="/">IoT Device Management Portal</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
            {accessToken ? (
              <>
                <Nav.Link href="/clients">Clients</Nav.Link>
                <Nav.Link href="/clients/add">Add Client</Nav.Link>
                <Nav.Link href="/devices">Devices</Nav.Link>
                <Nav.Link href="/devices/add">Add Devices</Nav.Link>
                <Nav.Link href="/measurements">Measurements</Nav.Link>
                <Nav.Link href="/measurements/add">Add Measurements</Nav.Link>
                <Nav.Link href="/logout">Logout</Nav.Link>
              </>
            ) : (
              <>
                <Nav.Link href="/login">Login</Nav.Link>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
