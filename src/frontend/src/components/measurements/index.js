import { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import Container from "react-bootstrap/esm/Container";
import Moment from "react-moment";
import { Link } from "react-router-dom";
import { getHistory } from "../../service/bidding-history";
import { toast } from "react-toastify";

export default function () {
  const [biddingHistory, setBiddingHistory] = useState([]);
  const getBiddingHistory = async () => {
    const res = await getHistory();
    if (res.success) {
      setBiddingHistory(res.data);
    } else {
      toast.error(res.error);
    }
  };

  useEffect(() => {
    getBiddingHistory();
  }, []);
  return (
    <Container className="mt-5">
      <h1 className="text-center">Measurements</h1>
      <hr />
      <Table striped>
        <thead>
          <tr>
            <th>#</th>
            <th>Product</th>
            <th>Bid Amount</th>
            <th>Bid Time</th>
          </tr>
        </thead>
        <tbody>
          {biddingHistory.map((b, i) => (
            <tr key={b.bidId}>
              <td>{biddingHistory.length - i}</td>
              <td>
                <Link to={`/products/${b.productId}`}>{b.productTitle}</Link>
              </td>
              <td>{b.amount}</td>
              <td>
                <Moment fromNow withTitle titleFormat="hh:mm:ss a M/D/yyyy">
                  {b.time}
                </Moment>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
