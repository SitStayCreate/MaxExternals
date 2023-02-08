#include "VelocityValues.h"
VelocityValues::VelocityValues() {
	for (int i = 0; i < 16; i++) { 
		set(i, 100);
	}
};
void VelocityValues::set(int interval, int velocity) {
	if (interval == 0) {
		velocities[0] = velocity;
	}
	else if (interval == 1) {
		velocities[1] = velocity;
	}
	else if (interval == 2) {
		velocities[2] = velocity;
	}
	else if (interval == 3) {
		velocities[3] = velocity;
	}
	else if (interval == 4) {
		velocities[4] = velocity;
	}
	else if (interval == 5) {
		velocities[5] = velocity;
	}
	else if (interval == 6) {
		velocities[6] = velocity;
	}
	else if (interval == 7) {
		velocities[7] = velocity;
	}
	else if (interval == 8) {
		velocities[8] = velocity;
	}
	else if (interval == 9) {
		velocities[9] = velocity;
	}
	else if (interval == 10) {
		velocities[10] = velocity;
	}
	else if (interval == 11) {
		velocities[11] = velocity;
	}
	else if (interval == 12) {
		velocities[12] = velocity;
	}
	else if (interval == 13) {
		velocities[13] = velocity;
	}
	else if (interval == 14) {
		velocities[14] = velocity;
	}
	else if (interval == 15) {
		velocities[15] = velocity;
	}
};
int  VelocityValues::getVelocity(int interval) {
	if (interval == 0) {
		return velocities[0];
	}
	else if (interval == 1) {
		return velocities[1];
	}
	else if (interval == 2) {
		return velocities[2];
	}
	else if (interval == 3) {
		return velocities[3];
	}
	else if (interval == 4) {
		return velocities[4];
	}
	else if (interval == 5) {
		return velocities[5];
	}
	else if (interval == 6) {
		return velocities[6];
	}
	else if (interval == 7) {
		return velocities[7];
	}
	else if (interval == 8) {
		return velocities[8];
	}
	else if (interval == 9) {
		return velocities[9];
	}
	else if (interval == 10) {
		return velocities[10];
	}
	else if (interval == 11) {
		return velocities[11];
	}
	else if (interval == 12) {
		return velocities[12];
	}
	else if (interval == 13) {
		return velocities[13];
	}
	else if (interval == 14) {
		return velocities[14];
	}
	else if (interval == 15) {
		return velocities[15];
	}
	else { 
		return -1;
	}
};
int  VelocityValues::getInterval(int velocity) {
	if (velocity == velocities[0]) {
		return 0;
	}
	else if (velocity == velocities[1]) {
		return 1;
	}
	else if (velocity == velocities[2]) {
		return 2;
	}
	else if (velocity == velocities[3]) {
		return 3;
	}
	else if (velocity == velocities[4]) {
		return 4;
	}
	else if (velocity == velocities[5]) {
		return 5;
	}
	else if (velocity == velocities[6]) {
		return 6;
	}
	else if (velocity == velocities[7]) {
		return 7;
	}
	else if (velocity == velocities[8]) {
		return 8;
	}
	else if (velocity == velocities[9]) {
		return 9;
	}
	else if (velocity == velocities[10]) {
		return 10;
	}
	else if (velocity == velocities[11]) {
		return 11;
	}
	else if (velocity == velocities[12]) {
		return 12;
	}
	else if (velocity == velocities[13]) {
		return 13;
	}
	else if (velocity == velocities[14]) {
		return 14;
	}
	else if (velocity == velocities[15]) {
		return 15;
	}
	else {
		return -1;
	}
};