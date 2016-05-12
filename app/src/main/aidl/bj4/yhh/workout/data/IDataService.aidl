package bj4.yhh.workout.data;
import bj4.yhh.workout.data.TrainData;
import bj4.yhh.workout.data.ScheduleDate;

interface IDataService {
  TrainData[] getAllTrainData();
  ScheduleDate addTrainData(in TrainData data, int y, int m, int d);
}