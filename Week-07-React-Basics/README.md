# Week 7: React Basics - Frontend Development

## 🎯 Learning Objectives
- Understand React fundamentals
- Build interactive UIs with components
- Manage state with useState hook
- Handle side effects with useEffect
- Integrate with backend APIs
- Create a full-stack application

## 📚 Topics Covered

### Day 31: React Setup & JSX
- Create React App
- JSX syntax
- Components
- Props

### Day 32: State & Props
- useState hook
- Event handling
- Forms

### Day 33: useEffect & API Integration
- Lifecycle with useEffect
- Fetch API / Axios
- Loading states

### Day 34: React Router
- Navigation
- Dynamic routing
- Protected routes

### Day 35: Forms & Validation
- Controlled components
- Form libraries
- Validation

## 🛠️ Prerequisites
- JavaScript ES6+ knowledge
- Node.js & npm installed
- Week 4 Spring Boot API (backend)

## 🚀 Quick Start

### Create React App:
```bash
cd Week-07-React-Basics
npx create-react-app student-dashboard
cd student-dashboard
npm start
```

### Install Dependencies:
```bash
npm install axios react-router-dom
```

### Run Development Server:
```bash
npm start
# Opens at http://localhost:3000
```

## 📁 Project Structure
```
student-dashboard/
├── public/
│   ├── index.html
│   └── favicon.ico
├── src/
│   ├── components/
│   │   ├── StudentList.js
│   │   ├── StudentForm.js
│   │   └── StudentCard.js
│   ├── services/
│   │   └── api.js
│   ├── App.js
│   ├── App.css
│   └── index.js
├── package.json
└── README.md
```

## 💻 Example: Student Dashboard

This React app connects to the Student Management API (Week 4) to:
- ✅ Display all students
- ✅ Add new student
- ✅ Edit student details
- ✅ Delete student
- ✅ Search students

### Full Stack Flow:
```
React (Port 3000) ←→ Spring Boot API (Port 8080) ←→ Database
```

## 🔑 Key Concepts

### 1. Component
```jsx
function StudentCard({ student }) {
  return (
    <div className="card">
      <h3>{student.name}</h3>
      <p>{student.email}</p>
    </div>
  );
}
```

### 2. State Management
```jsx
const [students, setStudents] = useState([]);
```

### 3. API Integration
```jsx
useEffect(() => {
  fetch('http://localhost:8080/api/students')
    .then(res => res.json())
    .then(data => setStudents(data));
}, []);
```

## 🧪 Testing the Full Stack App

1. **Start Backend** (Week 4):
   ```bash
   cd Week-04-Spring-Boot/student-management-api
   mvn spring-boot:run
   ```

2. **Start Frontend** (Week 7):
   ```bash
   cd Week-07-React-Basics/student-dashboard
   npm start
   ```

3. **Access**:
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console

## 📝 Exercises

### Exercise 1: Add Course Filter
Filter students by course dropdown

### Exercise 2: Pagination
Add pagination to student list

### Exercise 3: Dark Mode
Implement theme switcher

### Exercise 4: Form Validation
Add client-side validation

### Exercise 5: Error Handling
Display user-friendly error messages

## 🏆 Mini-Project: Complete CRUD Dashboard

Build a full-featured student management dashboard with:
- Create student form
- Student table with search
- Edit modal
- Delete confirmation
- Statistics cards
- Responsive design

## 🎨 Styling Options

### CSS Modules:
```jsx
import styles from './Student.module.css';
<div className={styles.card}>...</div>
```

### Popular Libraries:
```bash
# Material-UI
npm install @mui/material @emotion/react @emotion/styled

# Bootstrap
npm install bootstrap react-bootstrap

# Tailwind CSS
npm install -D tailwindcss postcss autoprefixer
```

## 🌐 Real-World Applications

This pattern is used in:
- **Admin Dashboards**: User management, analytics
- **E-commerce**: Product catalog, cart, checkout
- **Social Media**: Posts, comments, profiles
- **SaaS Products**: Customer portals, settings

## 🐛 Common Issues

### CORS Error:
```
Access to fetch at 'http://localhost:8080' from origin
'http://localhost:3000' has been blocked by CORS policy
```
**Solution**: Add `@CrossOrigin` in Spring Boot controller

### Port Already in Use:
```
Something is already running on port 3000
```
**Solution**: Kill process or use different port:
```bash
PORT=3001 npm start
```

## 📚 Resources
- [React Documentation](https://react.dev/)
- [React Router](https://reactrouter.com/)
- [Axios Documentation](https://axios-http.com/)

---

**Next Week**: Advanced React - Context API, Custom Hooks, Performance
