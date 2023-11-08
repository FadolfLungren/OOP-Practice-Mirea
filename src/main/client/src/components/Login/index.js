import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Carousel from 'react-material-ui-carousel'
import axios from "axios";
import {redirect} from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const SKOOFS = [
    "https://sun1-83.userapi.com/impg/LjJL75jptnt6PAU0mMBe-xYLkjkInXq5b3f8-w/ni2kr30ZIM0.jpg?size=707x800&quality=95&sign=d4f3955c64c6f738471d6aac517bccd5&c_uniq_tag=rIoSQYnfWYlmcRyZ-0UYSYibL_Xou2Jqw9nZNGyeHh4&type=album",
    "https://sun9-62.userapi.com/impg/pp6bqnxc2Nt_A_irf4GGHvjDzv5J_04amwohWQ/Pewi36qT3X0.jpg?size=576x719&quality=95&sign=4c50977f843f1c681e6c738462da3e2e&c_uniq_tag=2nkJgWp9-Bb-QG5Mknkzzd5MAlHiRENFdVaGM0hsBY0&type=album",
    "https://sun9-27.userapi.com/impg/ha6KgVatt4iBvzRabu21haIpcAutW6grCKizTQ/ZZHHZBNMp4k.jpg?size=805x595&quality=95&sign=16b35c11af209896c5e7118e6ef007a4&c_uniq_tag=eDGlSg3WMcP4ENjyy4pyoSHWCJO7G_3E2AxbYvp_A50&type=album",
]

function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                Your Website
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function SignInSide() {
    const navigate =useNavigate()


    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log({
            email: data.get('email'),
            password: data.get('password'),
        });

        axios.post('http://localhost:8080/users/login', {
                login: data.get('email'),
                password: data.get('password'),
            },{
            validateStatus: function (status) {
                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 200){
                navigate("/products")
            }

        })

    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Grid container component="main" sx={{ height: '100vh' }}>
                <CssBaseline />
                    <Grid
                        item
                        xs={false}
                        sm={4}
                        md={7}
                    >
                        <Carousel indicators={false}>
                            {SKOOFS.map((skyf)=>
                            <div style={{
                                backgroundImage: `url(${skyf})`,
                                backgroundRepeat: 'no-repeat',
                                backgroundColor: (t) =>
                                    t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
                                backgroundSize: 'cover',
                                backgroundPosition: 'center',
                                height:"100vh"
                            }}/>
                            )}
                        </Carousel>
                    </Grid>
                <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
                    <Box
                        sx={{
                            my: 8,
                            mx: 4,
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                        }}
                    >
                        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                            <LockOutlinedIcon />
                        </Avatar>
                        <Typography component="h1" variant="h5">
                            Sign in
                        </Typography>
                        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Username"
                                name="email"
                                autoComplete="email"
                                autoFocus
                            />
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                autoComplete="current-password"
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                sx={{ mt: 3, mb: 2 }}
                            >
                                Sign In
                            </Button>
                            <Grid container>
                                <Grid item>
                                    <Link href="/#/signup" variant="body2">
                                        {"Регистрация"}
                                    </Link>
                                </Grid>
                            </Grid>
                        </Box>
                    </Box>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}