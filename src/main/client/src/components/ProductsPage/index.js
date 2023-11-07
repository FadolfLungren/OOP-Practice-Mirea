import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import ProductsToolbar from "../ProductsToolbar";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import {createTheme} from "@mui/material/styles";
import {Pagination, Stack, ThemeProvider} from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";


const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 7, 8, 9, 10, 11 ,12];

const defaultTheme = createTheme();

function ResponsiveAppBar() {
    return (
        <ThemeProvider theme={defaultTheme}>
            <CssBaseline />
            <ProductsToolbar/>
            <Container maxWidth="xl">
                <Box
                    sx={{
                        pt: 8,
                        pb: 6,
                    }}
                />
                <Grid container spacing={4}>
                    {cards.map((card) => (
                        <Grid item key={card} xs={12} sm={6} md={2}>
                            <Card
                                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                            >
                                <CardMedia
                                    component="div"
                                    sx={{
                                        // 16:9
                                        pt: '56.25%',
                                    }}
                                    image="https://source.unsplash.com/random?wallpapers"
                                />
                                <CardContent sx={{ flexGrow: 1 }}>
                                    <Typography gutterBottom variant="h5" component="h2">
                                        Heading
                                    </Typography>
                                    <Typography>
                                        This is a media card. You can use this section to describe the
                                        content.
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small">Подробнее</Button>
                                    <Button size="small">В ведро</Button>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
                <Stack alignItems={"center"} sx={{
                    mt:3
                }}>
                    <Pagination component="div" count={10} shape="rounded" size={"large"}/>
                </Stack>



            </Container>
            <Box sx={{ bgcolor: 'background.paper', p: 6 }} component="footer">
                <Typography variant="h6" align="center" gutterBottom>
                    Footer
                </Typography>
                <Typography
                    variant="subtitle1"
                    align="center"
                    color="text.secondary"
                    component="p"
                >
                    Something here to give the footer a purpose!
                </Typography>
            </Box>
        </ThemeProvider>
    );
}
export default ResponsiveAppBar;
