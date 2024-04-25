import { Link } from "react-router-dom";
export default function Home() {
  return (
    <>
      <h2>Welcome to IoT Device Management!</h2>
      <div>
        Please <Link to="/login">login</Link> to get started.
      </div>
    </>
  );
}
