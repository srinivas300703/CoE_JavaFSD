// Home.jsx
import ProductCard from "../components/ProductCard";
const products = [
  {
    id: 1,
    name: "Product A",
    description: "Best quality product",
    rating: 4.5,
    price: 100,
    image: "https://placehold.co/150",
  },
  {
    id: 2,
    name: "Product B",
    description: "Highly recommended",
    rating: 4.0,
    price: 150,
    image: "https://placehold.co/150",
  },
  {
    id: 3,
    name: "Product C",
    description: "Great value for money",
    rating: 4.8,
    price: 200,
    image: "https://placehold.co/150",
  },
  {
    id: 4,
    name: "Product D",
    description: "Great value for money",
    rating: 4.8,
    price: 200,
    image: "https://placehold.co/150",
  },
  {
    id: 5,
    name: "Product E",
    description: "Great value for money",
    rating: 4.8,
    price: 200,
    image: "https://placehold.co/150",
  },
];

const Home = () => (
  <div className="p-4 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
    {products.map((product) => (
      <ProductCard key={product.id} product={product} />
    ))}
  </div>
);

export default Home;
