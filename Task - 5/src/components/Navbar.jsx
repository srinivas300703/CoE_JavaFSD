// Navbar.jsx
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";
const Navbar = () => {
  const { cart } = useCart();
  return (
    <nav className="p-4 bg-blue-500 text-white flex justify-between">
      <Link to="/">Home</Link>
      <Link to="/cart">Cart ({cart.length})</Link>
    </nav>
  );
};
export default Navbar;
