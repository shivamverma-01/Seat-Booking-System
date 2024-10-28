import React from 'react';

const SeatBox = ({ seat, isSelected, onSeatClick }) => {
    console.log(seat); // Log seat data to inspect the properties

    const getSeatStyle = () => {
        if (seat.reserved) {
            return { backgroundColor: '#555', color: 'white', textDecoration: 'line-through' };
        }
        if (seat.locked) {
            return { backgroundColor: '#999', color: 'white', opacity: 0.6 };
        }
        if (isSelected) {
            return { backgroundColor: '#4CAF50', color: 'white' };
        }
        return { backgroundColor: '#8BC34A', color: 'white' };
    };

    return (
        <div
            className="seat-box"
            style={getSeatStyle()}
            onClick={() => !(seat.reserved || seat.locked) && onSeatClick()}
            >
            {seat.seatNo}
        </div>
    );
};


export default SeatBox;
