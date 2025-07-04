import React, { useContext } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import { LoginPage } from './pages/LoginPage'
import { RegisterPage } from './pages/RegisterPage'
import { HomePage } from './pages/home/HomePage'
import { AuthContext } from './context/AuthContext'
import { Spinner } from 'react-bootstrap'
import { ProfilePage } from './pages/profile/ProfilePage'

export default function App() {
  const { user, initializing } = useContext(AuthContext);

  if (initializing) {
    return (
    <div className="d-flex h-100 align-items-center justify-content-center">
      <Spinner animation="border" />
    </div>
  )
  }

  return (
    <Routes>
      <Route
        path="/"
        element={
          user 
            ? <Navigate to="/home" replace /> 
            : <Navigate to="/login" replace />
        }
      />

      <Route
        path="/login"
        element={ user ? <Navigate to="/home" replace /> : <LoginPage /> }
      />

      <Route
        path="/register"
        element={ user ? <Navigate to="/home" replace /> : <RegisterPage /> }
      />

      <Route
        path="/home"
        element={ user ? <HomePage /> : <Navigate to="/login" replace /> }
      />

      <Route 
        path="/profile"
        element={ user ? <ProfilePage /> : <Navigate to="/login" replace /> }
      />

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  )
}