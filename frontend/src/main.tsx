import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import App from './App'
import { AuthProvider } from './context/AuthContext';


const rootEl = document.getElementById('root')
if (!rootEl) {
  throw new Error('No hay ningún <div id="root"> en index.html')
}

ReactDOM.createRoot(rootEl).render(
  <BrowserRouter>
    <AuthProvider>
      <App />
    </AuthProvider>
  </BrowserRouter>
)