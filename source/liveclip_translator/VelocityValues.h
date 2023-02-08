#pragma once
class VelocityValues {
private:
	int velocities[16];

public:
	VelocityValues();
	void set(int interval, int velocity);
	int getVelocity(int interval);
	int getInterval(int velocity);
};