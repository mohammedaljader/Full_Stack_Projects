import React from 'react';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import 'react-day-picker/lib/style.css';

export default function DateComponent() {
  return (
    <DayPickerInput
      dayPickerProps={{
        month: new Date(2021, 11),
        showWeekNumbers: true,
        todayButton: 'Today',
      }}
    />
  );
}