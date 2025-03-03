// CartPage.jsx
import Cart from "../components/Cart";
import { useCart } from "../context/CartContext";
const CartPage = () => {
  const { cart, dispatch } = useCart();
  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">Shopping Cart</h1>
      {cart.length === 0 ? <p>Cart is empty</p> : <Cart />}
      <h2 className="text-xl font-bold mt-4">Total: ${total.toFixed(2)}</h2>
      {cart.length > 0 && (
        <button
          className="bg-blue-600 text-white px-4 py-2 mt-4"
          onClick={() => dispatch({ type: "CLEAR_CART" })}
        >
          Clear Cart
        </button>
      )}
    </div>
  );
};
export default CartPage;
