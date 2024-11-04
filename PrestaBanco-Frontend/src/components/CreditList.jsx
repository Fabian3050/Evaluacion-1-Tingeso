import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import creditService from "../services/credit.service";
import userService from "../services/user.service";

const CreditListByUser = () => {
  const { userId } = useParams(); // Obtiene el ID del usuario de los parámetros de la URL
  const [credits, setCredits] = useState([]);
  const [userRut, setUserRut] = useState(""); // Estado para almacenar el RUT del usuario

  useEffect(() => {
    // Llama al servicio para obtener todas las solicitudes de crédito del usuario
    const fetchCredits = async () => {
      try {
        const response = await creditService.getById(userId);
        setCredits(response.data);
      } catch (error) {
        console.error("Error al obtener las solicitudes de crédito:", error);
      }
    };

    // Llama al servicio para obtener el RUT del usuario
    const fetchUserRut = async () => {
      try {
        const response = await userService.getById(userId);
        setUserRut(response.data.rut); // Almacena el RUT en el estado
      } catch (error) {
        console.error("Error al obtener los datos del usuario:", error);
      }
    };

    fetchCredits();
    fetchUserRut();
  }, [userId]);

  return (
    <TableContainer component={Paper} className="mt-5">
      <h2 className="text-center">Solicitudes de Crédito del Usuario</h2>
      <Table sx={{ minWidth: 650 }} size="medium" aria-label="credit table">
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Rut Cliente
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monto Solicitado
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Monto Aprobado
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Tasa de Interés
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Período de Pago (meses)
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Tipo de Crédito
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Fecha Creación Crédito
            </TableCell>
            <TableCell align="left" sx={{ fontWeight: "bold" }}>
              Estado de la Solicitud
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {credits.map((credit) => (
            <TableRow key={credit.id}>
              <TableCell align="left">{userRut}</TableCell>
              <TableCell align="left">{credit.requestedAmount}</TableCell>
              <TableCell align="left">{credit.approvedAmount}</TableCell>
              <TableCell align="left">{credit.interestRate}%</TableCell>
              <TableCell align="left">{credit.paymentPeriod} meses</TableCell>
              <TableCell align="left">{credit.creditType}</TableCell>
              <TableCell align="left">{credit.applicationDate}</TableCell>
              <TableCell align="left">{credit.status}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Link to="/register/list" className="btn btn-primary mt-3">
        Volver al registro de usuarios
      </Link>
    </TableContainer>
  );
};

export default CreditListByUser;
