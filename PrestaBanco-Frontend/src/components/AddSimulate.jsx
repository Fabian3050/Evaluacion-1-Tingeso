import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import SaveIcon from "@mui/icons-material/Save";
import userService from "../services/user.service";
import simulateService from "../services/simulate.service";

const AddSimulate = () => {
  const [m, setM] = useState("");
  const [p, setP] = useState("");
  const [r, setR] = useState("");
  const [n, setN] = useState("");
  const [totalPriceHome, setTotalPriceHome] = useState("");
  const [monthlyClientIncome, setMonthlyClientIncome] = useState("");
  const [creditType, setCreditType] = useState("");
  const [message, setMessage] = useState("");
  const [totalCreditCost, setTotalCreditCost] = useState("");
  const [totalMnthlyPay, setTotalMnthlyPay] = useState("");
  const [titleSimulateForm, setTitleUserForm] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();

  const handleSubmit = (s) => {
    s.preventDefault();

    const simulate = {
      m, p, r, n, totalPriceHome, monthlyClientIncome, creditType, message, totalCreditCost, totalMnthlyPay, id
    };

    // Crear nueva simulación
    simulateService
      .create(simulate)
      .then((response) => {
        console.log("Solicitud de simulación creada correctamente.", response.data);
        navigate("/simulate/list");
      })
      .catch((error) => {
        console.log("Ha ocurrido un error al intentar solicitar la simulación.", error);
      });

    // Obtener simulación
    simulateService
      .simulate(simulate)
      .then((response) => {
        console.log("Simulación realizada correctamente.", response.data);
        setM(response.m);
        setMessage(response.message);
        navigate("/simulateRealized");
      });
  };

  useEffect(() => {
    if (id) {
      setTitleUserForm("Editar simulación");
      userService
        .get(id)
        .then((simulate) => {
          setM(simulate.data.m);
          setP(simulate.data.p);
          setR(simulate.data.r);
          setN(simulate.data.n);
          setTotalPriceHome(simulate.data.totalPriceHome);
          setMonthlyClientIncome(simulate.data.monthlyClientIncome);
          setCreditType(simulate.data.creditType);
          setMessage(simulate.data.message);
          setTotalCreditCost(simulate.data.totalCreditCost);
          setTotalMnthlyPay(simulate.data.totalMnthlyPay);
        })
        .catch((error) => {
          console.log("Se ha producido un error.", error);
        });
    } else {
      setTitleUserForm("Nueva Simulación");
    }
  }, [id]);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
      onSubmit={handleSubmit}
    >
      <h3>{titleSimulateForm}</h3>
      <hr />

      <FormControl fullWidth>
        <TextField
          id="m"
          label="Monthly Payment"
          value={m}
          variant="standard"
          onChange={(s) => setM(s.target.value)}
          helperText="no rellenar este campo"
        />
      </FormControl>

      <FormControl fullWidth>
            <TextField
                id="p"
                label="Credit Amount"
                value={p}
                variant="standard"
                onChange={(s) => setP(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="r"
                label="Simulated Interest Rate"
                value={r}
                variant="standard"
                onChange={(s) => setR(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="n"
                label="Number of Pays"
                value={n}
                variant="standard"
                onChange={(s) => setN(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="totalPriceHome"
                label="Total Price Home"
                value={totalPriceHome}
                variant="standard"
                onChange={(s) => setTotalPriceHome(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="monthlyClientIncome"
                label="Monthly Client Income"
                value={monthlyClientIncome}
                variant="standard"
                onChange={(s) => setMonthlyClientIncome(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="creditType"
                label="Credit Type"
                value={creditType}
                variant="standard"
                onChange={(s) => setCreditType(s.target.value)}
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="message"
                label="Message"
                value={message}
                variant="standard"
                onChange={(s) => setMessage(s.target.value)}
                helperText="no rellenar este campo"
            />
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="totalCreditCost"
                label="Total Credit Cost"
                value={totalCreditCost}
                variant="standard"
                onChange={(s) => setTotalCreditCost(s.target.value)}
                helperText="no rellenar este campo"
            />  
        </FormControl>

        <FormControl fullWidth>
            <TextField
                id="totalMnthlyPay"
                label="Total Monthly Pay"
                value={totalMnthlyPay}
                variant="standard"
                onChange={(s) => setTotalMnthlyPay(s.target.value)}
                helperText="no rellenar este campo"
            />
        </FormControl>
      
      <FormControl>
        <br />
        <Button
          variant="contained"
          color="info"
          type="submit"
          style={{ marginLeft: "0.5rem" }}
          startIcon={<SaveIcon />}
        >
          Grabar
        </Button>
      </FormControl>

      <hr />
      <Link to="/simulate/list">Volver a la lista de simulaciones</Link>
    </Box>
  );
};

export default AddSimulate;
