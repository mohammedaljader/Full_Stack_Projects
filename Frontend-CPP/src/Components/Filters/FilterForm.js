import { useState } from 'react';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  TextField
} from '@mui/material';


export const FilterForm = () => {
  return (
      <Card>
        <CardHeader/>
        <Divider />
        <CardContent>
          <Grid container spacing={3} >
            <Grid item md={6} xs={12}>
              <TextField
                fullWidth
                label="First name"
                name="firstName"
                required
                value={values.firstName}
                variant="outlined"
              />
            </Grid>
          </Grid>
        </CardContent>
      </Card>
  );
};