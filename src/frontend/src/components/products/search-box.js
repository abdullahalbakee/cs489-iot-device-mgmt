import { useRef } from "react";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";

export default function ({ onSearch }) {
  const searchBoxRef = useRef(null);
  const handleSearch = (e) => {
    e.preventDefault();
    const searchValue = searchBoxRef.current.value;
    if (searchValue.trim().length > 0) onSearch(searchValue.trim());
    searchBoxRef.current.value = "";
  };
  return (
    <Form onSubmit={handleSearch}>
      <Row className="align-items-center">
        <Col xs="auto" style={{ width: "90%" }}>
          <Form.Label htmlFor="productSearchBox" visuallyHidden>
            Search products
          </Form.Label>
          <Form.Control
            ref={searchBoxRef}
            className="mb-2"
            id="productSearchBox"
            placeholder="Seach products here..."
          />
        </Col>
        <Col xs="auto" style={{ width: "10%" }}>
          <Button type="submit" className="mb-2">
            Search
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
