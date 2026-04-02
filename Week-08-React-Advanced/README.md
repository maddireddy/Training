# Week 08: Advanced React (Days 36-40)

## 🎯 Week Overview
Master advanced React patterns, state management, backend integration, and production-ready practices.

## 📅 Daily Projects

### Day 36: Context API & Global State
**Project**: Theme Switcher & User Session
- createContext & useContext
- Global state management
- Dark/light mode toggle
- User authentication context

### Day 37: Backend Integration
**Project**: Full CRUD Application
- Axios/Fetch API integration
- CORS handling
- Error boundaries
- Loading states & spinners
- Toasts/notifications

### Day 38: Advanced Hooks & Custom Hooks
**Project**: Reusable Hooks Library
- useReducer for complex state
- useMemo & useCallback
- Custom hooks (useAuth, useFetch, useLocalStorage)
- Performance optimization

### Day 39: UI Libraries & Styling
**Project**: Professional Dashboard
- Material-UI / Ant Design
- Styled Components
- CSS Modules
- Responsive design
- Mobile-first approach

### Day 40: Testing & Best Practices
**Project**: Tested React App
- React Testing Library
- Jest unit tests
- Integration tests
- Code splitting
- Lazy loading
- Production build optimization

## 🏗️ Week Project: E-Commerce Frontend

### Features
```
✅ Product catalog with search/filter
✅ Shopping cart management
✅ User authentication flow
✅ Order history
✅ Responsive design
✅ Dark/light theme
✅ Real-time updates
✅ Error handling
✅ Loading states
✅ 80%+ test coverage
```

### Tech Stack
```
React 18+
React Router v6
Context API / Redux
Axios
Material-UI / Ant Design
React Testing Library
Jest
```

### Project Structure
```
ecommerce-frontend/
├── src/
│   ├── components/
│   │   ├── common/       (Button, Input, Card)
│   │   ├── layout/       (Header, Footer, Sidebar)
│   │   └── features/     (ProductCard, CartItem)
│   ├── pages/
│   │   ├── Home.jsx
│   │   ├── Products.jsx
│   │   ├── Cart.jsx
│   │   └── Checkout.jsx
│   ├── context/
│   │   ├── AuthContext.jsx
│   │   ├── CartContext.jsx
│   │   └── ThemeContext.jsx
│   ├── hooks/
│   │   ├── useAuth.js
│   │   ├── useFetch.js
│   │   └── useLocalStorage.js
│   ├── services/
│   │   └── api.js
│   ├── utils/
│   └── App.jsx
├── public/
└── package.json
```

## 🧪 Testing Strategy
```javascript
// Component Test Example
test('renders product card with correct info', () => {
  render(<ProductCard product={mockProduct} />);
  expect(screen.getByText('Product Name')).toBeInTheDocument();
  expect(screen.getByText('₹999')).toBeInTheDocument();
});

// Integration Test
test('adds product to cart', async () => {
  render(<ProductPage />);
  const addButton = screen.getByText('Add to Cart');
  fireEvent.click(addButton);
  await waitFor(() => {
    expect(screen.getByText('Added to cart')).toBeInTheDocument();
  });
});
```

## 🎓 Learning Outcomes
- Master Context API
- Build custom hooks
- Integrate with REST APIs
- Use UI libraries
- Write comprehensive tests
- Optimize performance
- Deploy production apps

---
**Next**: Week 09 - AWS & Cloud
