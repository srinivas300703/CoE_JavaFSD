// ProductCard.jsx
import { useCart } from "../context/CartContext";
const ProductCard = ({ product }) => {
  const { cart, dispatch } = useCart();
  const inCart = cart.some((item) => item.id === product.id);
  return (
    <div className="border p-4 bg-white shadow-lg rounded-lg flex flex-col items-center text-center">
      <img
        src={product.image}
        alt={product.name}
        className="w-full h-40 object-cover rounded-t-lg"
      />
      <div className="p-4 w-full">
        <h2 className="text-lg font-bold">{product.name}</h2>
        <p className="text-gray-600">{product.description}</p>
        <p className="text-yellow-500">⭐ {product.rating}</p>
        <p className="font-semibold">${product.price}</p>
        {inCart ? (
          <button className="bg-gray-500 text-white px-4 py-2 mt-2 cursor-not-allowed w-full">
            Added
          </button>
        ) : (
          <button
            className="bg-green-500 text-white px-4 py-2 mt-2 w-full"
            onClick={() => dispatch({ type: "ADD_TO_CART", payload: product })}
          >
            Add to Cart
          </button>
        )}
      </div>
    </div>
  );
};
export default ProductCard;
