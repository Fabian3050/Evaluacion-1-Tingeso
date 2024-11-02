// src/components/RequestCredit.js

import React, { useState } from "react";
import Button from "@mui/material/Button";
import { useParams } from "react-router-dom";

const RequestCredit = () => {
  const { userId } = useParams();
  const [creditType, setCreditType] = useState("");
  const [documents, setDocuments] = useState([]);

  const creditRequirements = {
    "Primera Vivienda": [
      "Comprobante de ingresos",
      "Certificado de avaluó",
      "Historial crediticio",
    ],
    "Segunda Vivienda": [
      "Comprobante de ingresos",
      "Certificado de avaluó",
      "Escritura de la primera vivienda",
      "Historial crediticio",
    ],
    "Propiedades Comerciales": [
      "Estado financiero del negocio",
      "Comprobante de ingresos",
      "Certificado de avaluó",
      "Plan de negocios",
    ],
    "Remodelación": [
      "Comprobante de ingresos",
      "Presupuesto de la remodelación",
      "Certificado de avaluó actualizado",
    ],
  };

  const handleCreditTypeChange = (e) => {
    const selectedType = e.target.value;
    setCreditType(selectedType);
    setDocuments(creditRequirements[selectedType] || []);
  };

  const handleSubmit = () => {
    // Aquí puedes procesar la solicitud con los documentos cargados
    alert("Solicitud de crédito enviada.");
  };

  return (
    <div className="container mt-4">
      <h2>Solicitud de Crédito para Usuario ID: {userId}</h2>
      <div className="mb-3">
        <label htmlFor="creditType" className="form-label">Tipo de Crédito:</label>
        <select
          id="creditType"
          className="form-select"
          value={creditType}
          onChange={handleCreditTypeChange}
        >
          <option value="">Seleccionar</option>
          <option value="Primera Vivienda">Primera Vivienda</option>
          <option value="Segunda Vivienda">Segunda Vivienda</option>
          <option value="Propiedades Comerciales">Propiedades Comerciales</option>
          <option value="Remodelación">Remodelación</option>
        </select>
      </div>

      {documents.length > 0 && (
        <div className="mb-3">
          <h4>Documentos Requeridos</h4>
          <ul>
            {documents.map((doc, index) => (
              <li key={index}>
                {doc} <input type="file" />
              </li>
            ))}
          </ul>
        </div>
      )}

      <Button variant="contained" color="primary" onClick={handleSubmit}>
        Enviar Solicitud
      </Button>
    </div>
  );
};

export default RequestCredit;
