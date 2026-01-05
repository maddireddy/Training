/**
 * API Service - Week 7: React Frontend
 *
 * This file contains all API calls to the Spring Boot backend.
 * Centralized API logic makes it easy to maintain and update.
 *
 * Backend: http://localhost:8080/api/students
 *
 * Real-world: Similar pattern used in all production React apps
 */

import axios from 'axios';

// Base URL for API (from Spring Boot backend)
const API_BASE_URL = 'http://localhost:8080/api/students';

/**
 * Create axios instance with default config
 * Real-world: Add authentication headers, interceptors here
 */
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/**
 * API Methods
 * Each method corresponds to a backend endpoint
 */

const studentAPI = {
  /**
   * Get all students
   * GET /api/students
   * @returns {Promise} List of all students
   */
  getAllStudents: async () => {
    try {
      const response = await api.get('/');
      return response.data;
    } catch (error) {
      console.error('Error fetching students:', error);
      throw error;
    }
  },

  /**
   * Get student by ID
   * GET /api/students/{id}
   * @param {number} id - Student ID
   * @returns {Promise} Student object
   */
  getStudentById: async (id) => {
    try {
      const response = await api.get(`/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching student ${id}:`, error);
      throw error;
    }
  },

  /**
   * Create new student
   * POST /api/students
   * @param {Object} student - Student data
   * @returns {Promise} Created student
   */
  createStudent: async (student) => {
    try {
      const response = await api.post('/', student);
      return response.data;
    } catch (error) {
      console.error('Error creating student:', error);
      throw error;
    }
  },

  /**
   * Update existing student
   * PUT /api/students/{id}
   * @param {number} id - Student ID
   * @param {Object} student - Updated student data
   * @returns {Promise} Updated student
   */
  updateStudent: async (id, student) => {
    try {
      const response = await api.put(`/${id}`, student);
      return response.data;
    } catch (error) {
      console.error(`Error updating student ${id}:`, error);
      throw error;
    }
  },

  /**
   * Delete student
   * DELETE /api/students/{id}
   * @param {number} id - Student ID
   * @returns {Promise}
   */
  deleteStudent: async (id) => {
    try {
      await api.delete(`/${id}`);
      return true;
    } catch (error) {
      console.error(`Error deleting student ${id}:`, error);
      throw error;
    }
  },

  /**
   * Search students by name
   * GET /api/students/search?name={query}
   * @param {string} name - Search query
   * @returns {Promise} List of matching students
   */
  searchStudents: async (name) => {
    try {
      const response = await api.get('/search', {
        params: { name },
      });
      return response.data;
    } catch (error) {
      console.error('Error searching students:', error);
      throw error;
    }
  },

  /**
   * Get students by course
   * GET /api/students/course/{course}
   * @param {string} course - Course name
   * @returns {Promise} List of students in course
   */
  getStudentsByCourse: async (course) => {
    try {
      const response = await api.get(`/course/${course}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching students for course ${course}:`, error);
      throw error;
    }
  },

  /**
   * Get active students only
   * GET /api/students/active
   * @returns {Promise} List of active students
   */
  getActiveStudents: async () => {
    try {
      const response = await api.get('/active');
      return response.data;
    } catch (error) {
      console.error('Error fetching active students:', error);
      throw error;
    }
  },

  /**
   * Get statistics
   * GET /api/students/stats
   * @returns {Promise} Statistics object
   */
  getStatistics: async () => {
    try {
      const response = await api.get('/stats');
      return response.data;
    } catch (error) {
      console.error('Error fetching statistics:', error);
      throw error;
    }
  },
};

export default studentAPI;

/*
 * USAGE EXAMPLE IN REACT COMPONENT:
 *
 * import studentAPI from './services/api';
 *
 * // In your component:
 * useEffect(() => {
 *   const fetchStudents = async () => {
 *     try {
 *       const data = await studentAPI.getAllStudents();
 *       setStudents(data);
 *     } catch (error) {
 *       console.error('Failed to fetch students');
 *     }
 *   };
 *   fetchStudents();
 * }, []);
 */

/*
 * REAL-WORLD ENHANCEMENTS:
 *
 * 1. Authentication:
 * api.interceptors.request.use((config) => {
 *   const token = localStorage.getItem('authToken');
 *   if (token) {
 *     config.headers.Authorization = `Bearer ${token}`;
 *   }
 *   return config;
 * });
 *
 * 2. Error Handling:
 * api.interceptors.response.use(
 *   response => response,
 *   error => {
 *     if (error.response.status === 401) {
 *       // Redirect to login
 *     }
 *     return Promise.reject(error);
 *   }
 * );
 *
 * 3. Loading States:
 * - Show spinner while API call is in progress
 * - Display success/error messages
 *
 * 4. Caching:
 * - Cache frequently accessed data
 * - Implement stale-while-revalidate pattern
 */
