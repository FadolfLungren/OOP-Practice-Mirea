import React from 'react';
import Grid from "@mui/material/Grid";
import CssBaseline from "@mui/material/CssBaseline";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import ProductsToolbar from "../ProductsToolbar";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";

const defaultTheme = createTheme();


const bucketItems = [1,2,3,4,5]
const ProfilePage = () => {
    return (
    <div>
        <ProductsToolbar/>

            <Container maxWidth="xl">
                <ThemeProvider theme={defaultTheme}>
                    <CssBaseline />

                    <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }} >
                        <Grid item xs={4} sx={{mt:8}}>
                            <Card variant="outlined" sx={{
                                display:"flex",
                                flexDirection:"column",
                                p:2,
                                height:"90vh"
                            }}>
                                <CardMedia
                                    sx={{ height: 140

                                    }}
                                    image="https://source.unsplash.com/random?wallpapers"
                                    title="green iguana"
                                />
                                <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        Lizard
                                    </Typography>
                                    <Typography variant="body2" color="text.secondary">
                                        Lizards are a widespread group of squamate reptiles, with over 6,000
                                        species, ranging across all continents except Antarctica
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small">Убить себя нахуй</Button>
                                    <Button size="small">Выйти из аккаунта</Button>
                                </CardActions>

                            </Card>
                        </Grid>
                        <Grid item xs={8} sx={{mt:8}}>
                            <Grid container rowSpacing={2} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                                {bucketItems.map((card)=>
                                    <Grid item xs={12} >

                                        <Card variant="outlined" sx={{
                                            display:"flex",
                                            p:2
                                        }}>
                                            <CardMedia
                                                sx={{ height: 140,
                                                    width:"35%",
                                                }}
                                                image="https://source.unsplash.com/random?wallpapers"
                                                title="green iguana"
                                            />
                                            <CardContent>
                                                <Typography gutterBottom variant="h5" component="div">
                                                    Lizard
                                                </Typography>
                                                <Typography variant="body2" color="text.secondary">
                                                    Lizards are a widespread group of squamate reptiles, with over 6,000
                                                    species, ranging across all continents except Antarctica
                                                </Typography>
                                            </CardContent>
                                            <CardActions>
                                                <Button size="small">Убрать</Button>
                                                <Button size="small">ИЩО</Button>
                                            </CardActions>

                                        </Card>
                                    </Grid>
                                )}
                            </Grid>
                        </Grid>
                    </Grid>
                </ThemeProvider>
            </Container>
    </div>
    );
};

export default ProfilePage;