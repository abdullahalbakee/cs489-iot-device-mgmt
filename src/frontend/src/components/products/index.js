import { useEffect, useState } from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import SingleProduct from "./single-product";
import SearchBox from "./search-box";
import { getProducts } from "../../service/products";

export default function () {
  const [products, setProducts] = useState([]);
  const [isSearchMode, setIsSearchMode] = useState(false);
  const [searchQuery, setSearchQuery] = useState(null);

  const getProductsFromServer = async (query) => {
    const res = await getProducts(query);
    if (res.success) {
      setProducts(res.data);
    } else {
    }
  };

  useEffect(() => {
    //getProductsFromServer(null);
  }, []);

  const handleSearch = async (searchString) => {
    setSearchQuery(searchString);
    getProductsFromServer(searchString);
    setIsSearchMode(true);
  };

  const clearSearch = async (e) => {
    e.preventDefault();
    setSearchQuery(null);
    getProductsFromServer(null);
    setIsSearchMode(false);
  };

  return (
    <>
      <div className="mt-5"></div>
      <SearchBox onSearch={handleSearch} />
      {isSearchMode && (
        <div>
          Showing search results for: <strong>{searchQuery}</strong>{" "}
          <Button variant="link" onClick={clearSearch}>
            Clear Search
          </Button>
        </div>
      )}
      <hr />
      <Row xs={1} md={2} lg={4} className="g-4">
        {!products ||
          (products.length === 0 && <Col>No products are available yet!</Col>)}
        {products.map((p, idx) => (
          <Col key={idx}>
            <SingleProduct product={p} showDetailsLink={true} />
          </Col>
        ))}
      </Row>
    </>
  );
}
